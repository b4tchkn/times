package com.b4tchkn.times.ui

import com.b4tchkn.times.SideEffect

sealed class CommonSideEffect : SideEffect {
    data class Load(val loadingStatus: LoadingStatus) : CommonSideEffect()
    object Error : CommonSideEffect()
}
