package com.b4tchkn.times.ui

import com.b4tchkn.times.model.SideEffect

sealed class CommonSideEffect : SideEffect {
    object Error : CommonSideEffect()
}
