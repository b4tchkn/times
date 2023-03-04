package com.b4tchkn.times.ui.screen.top

import com.b4tchkn.times.domain.GetCurrentWeatherUseCase
import com.b4tchkn.times.domain.GetGoogleTopicNewsUseCase
import com.b4tchkn.times.domain.GetNewsTopHeadlinesUseCase
import com.b4tchkn.times.model.CurrentWeatherModel
import com.b4tchkn.times.model.GoogleNewsRssModel
import com.b4tchkn.times.model.GoogleNewsServiceTopicTypeModel
import com.b4tchkn.times.model.Producer
import com.b4tchkn.times.ui.LoadingStatus
import com.b4tchkn.times.ui.screen.top.model.TopAction
import com.b4tchkn.times.ui.screen.top.model.TopAction.UpdateLocation
import com.b4tchkn.times.ui.screen.top.model.TopSideEffect
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first

class TopProducer @Inject constructor(
    private val getGoogleTopicNewsUseCase: GetGoogleTopicNewsUseCase,
    private val getNewsTopHeadlinesUseCase: GetNewsTopHeadlinesUseCase,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
) : Producer<TopUiState, TopAction, TopSideEffect>() {
    private val _sideEffect = MutableSharedFlow<TopSideEffect>()
    override val sideEffect: SharedFlow<TopSideEffect>
        get() = _sideEffect.asSharedFlow()

    override suspend fun reduce(state: TopUiState, action: TopAction): TopUiState {
        return when (action) {
            is TopAction.Init -> fetch(state, action)
            is TopAction.Refresh -> fetch(state, action)
            is TopAction.InitLoad -> state.copy(loadingStatus = LoadingStatus.Init(true))
            is TopAction.RefreshLoad -> state.copy(loadingStatus = LoadingStatus.Refresh(true))
            is UpdateLocation -> fetch(state, action)
        }
    }

    private suspend fun fetch(state: TopUiState, action: TopAction): TopUiState {
        val googleTopicNewsUseCases = mutableListOf<Flow<Result<GoogleNewsRssModel>>>()
        GoogleNewsServiceTopicTypeModel.values().forEach {
            googleTopicNewsUseCases.add(getGoogleTopicNewsUseCase(it))
        }

        if (action is UpdateLocation) {
            val latitude = action.location.first
            val longitude = action.location.second

            val currentWeather: Flow<Result<CurrentWeatherModel?>> =
                getCurrentWeatherUseCase(
                    latitude = latitude,
                    longitude = longitude,
                )
            return try {
                state.copy(
                    currentWeather = currentWeather.first().getOrThrow(),
                    loadingStatus = loadedStatusFromAction(action),
                )
            } catch (e: Exception) {
                state.copy(
                    error = true,
                    loadingStatus = loadedStatusFromAction(action),
                )
            }
        }

        val topHeadlines = getNewsTopHeadlinesUseCase()
        val googleTopicNewsFlows = combine(flows = googleTopicNewsUseCases) { it }

        return combine(
            googleTopicNewsFlows,
            topHeadlines,
        ) { googleTopicNewsResults, topHeadlinesResult ->
            val newState = try {
                state.copy(
                    googleTopicNews = googleTopicNewsResults.map { it.getOrThrow() },
                    topHeadlines = topHeadlinesResult.getOrThrow(),
                    loadingStatus = loadedStatusFromAction(action),
                )
            } catch (e: Exception) {
                state.copy(
                    error = true,
                    loadingStatus = loadedStatusFromAction(action),
                )
            }
            newState
        }.first()
    }

    private fun loadedStatusFromAction(action: TopAction) = when (action) {
        TopAction.Init -> LoadingStatus.Init(loading = false)
        TopAction.InitLoad -> LoadingStatus.Init(loading = false)
        TopAction.Refresh -> LoadingStatus.Refresh(loading = false)
        TopAction.RefreshLoad -> LoadingStatus.Refresh(false)
        is UpdateLocation -> LoadingStatus.Refresh(loading = false)
    }
}
