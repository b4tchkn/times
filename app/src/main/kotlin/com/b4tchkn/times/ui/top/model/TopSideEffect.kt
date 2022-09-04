package com.b4tchkn.times.ui.top.model

import com.b4tchkn.times.SideEffect

sealed interface TopSideEffect : SideEffect {
    data class Load(val loading: Boolean) : TopSideEffect
    object Error : TopSideEffect
}
