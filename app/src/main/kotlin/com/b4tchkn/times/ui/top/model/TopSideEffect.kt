package com.b4tchkn.times.ui.top.model

import com.b4tchkn.times.SideEffect
import com.b4tchkn.times.ui.CommonSideEffect

sealed interface TopSideEffect : SideEffect {
    data class Common(val commonSideEffect: CommonSideEffect) : TopSideEffect
}
