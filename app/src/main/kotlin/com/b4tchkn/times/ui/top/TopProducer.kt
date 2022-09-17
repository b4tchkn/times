package com.b4tchkn.times.ui.top

import com.b4tchkn.times.data.GoogleNewsServiceTopicType
import com.b4tchkn.times.domain.GetCurrentWeatherUseCase
import com.b4tchkn.times.domain.GetGoogleTopicNewsUseCase
import com.b4tchkn.times.domain.GetNewsTopHeadlinesUseCase
import com.b4tchkn.times.model.GoogleNewsRssModel
import com.b4tchkn.times.model.Producer
import com.b4tchkn.times.ui.CommonSideEffect
import com.b4tchkn.times.ui.LoadingStatus
import com.b4tchkn.times.ui.top.model.TopAction
import com.b4tchkn.times.ui.top.model.TopSideEffect
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first

class TopProducer @Inject constructor(
    private val getGoogleTopicNewsUseCase: GetGoogleTopicNewsUseCase,
    private val getNewsTopHeadlinesUseCase: GetNewsTopHeadlinesUseCase,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
) : Producer<TopState, TopAction, TopSideEffect>() {
    private val _sideEffect = MutableSharedFlow<TopSideEffect>(replay = 1)
    override val sideEffect: SharedFlow<TopSideEffect>
        get() = _sideEffect.asSharedFlow()

    override suspend fun reduce(state: TopState, action: TopAction): TopState {
        return when (action) {
            is TopAction.Init -> fetch(state, action)
            is TopAction.Refresh -> fetch(state, action)
        }
    }

    private suspend fun fetch(state: TopState, action: TopAction): TopState {
        val googleTopicNewsUseCases = mutableListOf<Flow<Result<GoogleNewsRssModel>>>()
        GoogleNewsServiceTopicType.values().forEach {
            googleTopicNewsUseCases.add(getGoogleTopicNewsUseCase(it))
        }
        val topHeadlines = getNewsTopHeadlinesUseCase()
        val currentWeather = getCurrentWeatherUseCase(
            latitude = 35.658034,
            longitude = 139.701636,
        )
        val googleTopicNewsFlows = combine(flows = googleTopicNewsUseCases) { it }

        return combine(
            googleTopicNewsFlows,
            topHeadlines,
            currentWeather
        ) { googleTopicNewsResults, topHeadlinesResult, currentWeatherResult ->
            emitLoad(true, action)
            val newState = try {
                state.copy(
                    googleTopicNews = googleTopicNewsResults.map { it.getOrThrow() },
                    topHeadlines = topHeadlinesResult.getOrThrow(),
                    currentWeather = currentWeatherResult.getOrThrow(),
                )
            } catch (e: Exception) {
                state.copy(error = true)
            }
            emitLoad(false, action)
            newState
        }.first()
    }

    private suspend fun emitLoad(loading: Boolean, action: TopAction) {
        val loadingStatus = when (action) {
            TopAction.Init -> {
                LoadingStatus.Init(
                    loading = loading,
                )
            }
            TopAction.Refresh -> {
                LoadingStatus.Refresh(
                    loading = loading,
                )
            }
        }

        _sideEffect.emit(
            TopSideEffect.Common(
                commonSideEffect = CommonSideEffect.Load(
                    loadingStatus = loadingStatus,
                ),
            )
        )
    }
}
