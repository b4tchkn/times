package com.b4tchkn.times.ui.screen.weather

import com.b4tchkn.times.domain.GetCurrentWeatherUseCase
import com.b4tchkn.times.model.Producer
import com.b4tchkn.times.ui.LoadingStatus
import com.b4tchkn.times.ui.screen.weather.model.WeatherAction
import com.b4tchkn.times.ui.screen.weather.model.WeatherSideEffect
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first

class WeatherProducer @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
) : Producer<WeatherUiState, WeatherAction, WeatherSideEffect>() {
    private val _sideEffect = MutableSharedFlow<WeatherSideEffect>()
    override val sideEffect: SharedFlow<WeatherSideEffect>
        get() = _sideEffect.asSharedFlow()

    override suspend fun reduce(state: WeatherUiState, action: WeatherAction): WeatherUiState {
        return when (action) {
            WeatherAction.Init -> fetch(state, action)
            WeatherAction.InitLoad -> fetch(state, action)
            WeatherAction.Refresh -> state.copy(loadingStatus = LoadingStatus.Init(true))
            WeatherAction.RefreshLoad -> state.copy(loadingStatus = LoadingStatus.Refresh(true))
        }
    }

    private suspend fun fetch(state: WeatherUiState, action: WeatherAction): WeatherUiState {
        val currentWeather = getCurrentWeatherUseCase(
            latitude = 35.658034,
            longitude = 139.701636,
        )

        return combine(currentWeather) {
            val newState = try {
                state.copy(
                    currentWeather = it.first().getOrThrow()
                )
            } catch (e: Exception) {
                state.copy(
                    error = true,
                    loadingStatus = loadedStatusFromAction(action)
                )
            }
            newState
        }.first()
    }

    private fun loadedStatusFromAction(action: WeatherAction) = when (action) {
        WeatherAction.Init -> LoadingStatus.Init(loading = false)
        WeatherAction.InitLoad -> LoadingStatus.Init(loading = false)
        WeatherAction.Refresh -> LoadingStatus.Refresh(loading = false)
        WeatherAction.RefreshLoad -> LoadingStatus.Refresh(false)
    }
}
