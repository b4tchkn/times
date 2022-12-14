package com.b4tchkn.times.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.b4tchkn.times.R
import com.b4tchkn.times.model.Action
import com.b4tchkn.times.model.SideEffect
import com.b4tchkn.times.model.State
import com.b4tchkn.times.model.StoreViewModel
import com.b4tchkn.times.ui.component.ErrorScreen
import com.b4tchkn.times.ui.component.LoadIndicator
import com.b4tchkn.times.ui.screen.top.model.TopSideEffect

@Composable
fun <ACTION : Action, STATE : State, SIDE_EFFECT : SideEffect> Connector(
    modifier: Modifier = Modifier.fillMaxSize(),
    snackbarHostState: SnackbarHostState?,
    storeViewModel: StoreViewModel<ACTION, STATE, SIDE_EFFECT>,
    initAction: ACTION,
    initActionLoad: ACTION,
    refreshActionLoad: ACTION,
    screen: @Composable (state: STATE) -> Unit,
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val state by storeViewModel.uiState.collectAsState()
    var inited by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            storeViewModel.dispatch(if (inited) refreshActionLoad else initActionLoad)
            storeViewModel.dispatch(initAction)
            inited = true

            storeViewModel.sideEffect.collect {
                if (it is TopSideEffect.Common) {
                    when (it.commonSideEffect) {
                        CommonSideEffect.Error -> snackbarHostState?.showSnackbar(
                            message = context.getString(
                                R.string.snackbar_error
                            )
                        )
                    }
                }
            }
        }
    }

    UIHandler(
        modifier = modifier,
        error = state.error,
        loadingStatus = state.loadingStatus,
    ) {
        screen(state)
    }
}

@Composable
private fun UIHandler(
    modifier: Modifier,
    error: Boolean,
    loadingStatus: LoadingStatus?,
    screen: @Composable () -> Unit
) {
    val isInitLoading = loadingStatus is LoadingStatus.Init && loadingStatus.loading
    if (isInitLoading) {
        LoadIndicator()
        return
    }

    if (error) {
        ErrorScreen(modifier = modifier)
    } else {
        val isRefreshLoading = if (loadingStatus is LoadingStatus.Refresh) {
            loadingStatus.loading
        } else {
            false
        }

        Box(modifier = modifier) {
            screen()
            if (isRefreshLoading) {
                LoadIndicator()
            }
        }
    }
}
