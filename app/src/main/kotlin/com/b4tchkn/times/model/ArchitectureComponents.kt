package com.b4tchkn.times.model

import androidx.lifecycle.ViewModel
import com.b4tchkn.times.ui.LoadingStatus
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

abstract class StoreViewModel<in ACTION : Action, out STATE : State, SIDE_EFFECT : SideEffect> :
    ViewModel() {
    abstract val uiState: StateFlow<STATE>
    abstract val sideEffect: SharedFlow<SIDE_EFFECT>
    abstract fun dispatch(action: ACTION)
}

interface SideEffect

abstract class State {
    abstract val error: Boolean
    abstract val loadingStatus: LoadingStatus
}

interface Action

abstract class Producer<STATE : State, ACTION : Action, SIDE_EFFECT : SideEffect> {
    abstract val sideEffect: SharedFlow<SIDE_EFFECT>
    abstract suspend fun reduce(state: STATE, action: ACTION): STATE
}
