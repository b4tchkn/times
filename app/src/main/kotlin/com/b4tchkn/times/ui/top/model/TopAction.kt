package com.b4tchkn.times.ui.top.model

import com.b4tchkn.times.Action

sealed interface TopAction : Action {
    object Init : TopAction
    object Refresh : TopAction
}
