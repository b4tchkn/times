package com.b4tchkn.times.ui.screen.top

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.b4tchkn.times.ui.Connector
import com.b4tchkn.times.ui.screen.top.model.TopAction
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@Destination
@Composable
fun TopScreenConnector(
    viewModel: TopStoreViewModel = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()
    val snackbarHostState = scaffoldState.snackbarHostState

    LaunchedEffect(key1 = Unit) {
        viewModel.dispatch(TopAction.InitLoad)
    }

    Scaffold(
        scaffoldState = scaffoldState,
    ) {
        Connector(
            snackbarHostState = snackbarHostState,
            storeViewModel = viewModel,
            initAction = TopAction.Init,
        ) { state ->
            TopScreen(
                paddingValues = it,
                topState = state,
                onRefreshed = {
                    viewModel.dispatch(TopAction.RefreshLoad)
                    viewModel.dispatch(TopAction.Refresh)
                }
            )
        }
    }
}
