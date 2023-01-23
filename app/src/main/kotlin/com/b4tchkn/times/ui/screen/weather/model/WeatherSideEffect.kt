package com.b4tchkn.times.ui.screen.weather.model

import com.b4tchkn.times.model.SideEffect
import com.b4tchkn.times.ui.CommonSideEffect

sealed interface WeatherSideEffect : SideEffect {
    data class Common(val commonSideEffect: CommonSideEffect) : WeatherSideEffect
}
