package com.b4tchkn.times.ui.screen.weather.model

import com.b4tchkn.times.model.Action

sealed interface WeatherAction : Action {
    object Init : WeatherAction
    object Refresh : WeatherAction
    object InitLoad : WeatherAction
    object RefreshLoad : WeatherAction
}
