package com.b4tchkn.times.ui.top

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.b4tchkn.times.ui.Connector
import com.b4tchkn.times.ui.top.model.TopAction

@Composable
fun TopScreenConnector(
    viewModel: TopStoreViewModel = viewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val snackbarHostState = scaffoldState.snackbarHostState

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
            )
        }
    }
}
