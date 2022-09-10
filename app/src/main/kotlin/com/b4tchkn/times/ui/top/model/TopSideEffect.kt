package com.b4tchkn.times.ui.top.model

import com.b4tchkn.times.SideEffect
import com.b4tchkn.times.ui.LoadingStatus

sealed interface TopSideEffect : SideEffect {
    data class Load(val loadingStatus: LoadingStatus) : TopSideEffect
    object Error : TopSideEffect
}
