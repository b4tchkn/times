package com.b4tchkn.times.ui.screen.top.model

import com.b4tchkn.times.model.Action

sealed interface TopAction : Action {
    object Init : TopAction
    object Refresh : TopAction
    object InitLoad : TopAction
    object RefreshLoad : TopAction
}
