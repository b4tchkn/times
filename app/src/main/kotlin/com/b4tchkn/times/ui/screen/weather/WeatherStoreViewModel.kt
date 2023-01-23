package com.b4tchkn.times.ui.screen.weather

import androidx.lifecycle.viewModelScope
import com.b4tchkn.times.model.CurrentWeatherModel
import com.b4tchkn.times.model.State
import com.b4tchkn.times.model.StoreViewModel
import com.b4tchkn.times.ui.LoadingStatus
import com.b4tchkn.times.ui.screen.weather.model.WeatherAction
import com.b4tchkn.times.ui.screen.weather.model.WeatherSideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class WeatherStoreViewModel @Inject constructor(
    private val producer: WeatherProducer,
) : StoreViewModel<WeatherAction, WeatherUiState, WeatherSideEffect>() {
    private val _uiState = MutableStateFlow(WeatherUiState.init)
    override val uiState: StateFlow<WeatherUiState>
        get() = _uiState.asStateFlow()

    override val sideEffect: SharedFlow<WeatherSideEffect>
        get() = producer.sideEffect

    init {
        dispatch(WeatherAction.InitLoad)
        dispatch(WeatherAction.Init)
    }

    override fun dispatch(action: WeatherAction) {
        viewModelScope.launch {
            _uiState.value = producer.reduce(_uiState.value, action)
        }
    }
}

data class WeatherUiState(
    val currentWeather: CurrentWeatherModel,
    override val error: Boolean,
    override val loadingStatus: LoadingStatus,
) : State() {
    companion object {
        val init = WeatherUiState(
            currentWeather = CurrentWeatherModel.defaultInstance,
            error = false,
            loadingStatus = LoadingStatus.Init(loading = false),
        )
    }
}
