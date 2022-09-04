package com.b4tchkn.times.ui.top

import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.b4tchkn.times.ui.top.model.TopAction
import com.b4tchkn.times.ui.top.model.TopSideEffect

@Composable
fun TopScreen(
    viewModel: TopStoreViewModel = viewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val state by viewModel.uiState.collectAsState()
    var loading by remember { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState()
    val snackbarHostState = scaffoldState.snackbarHostState

    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            viewModel.dispatch(TopAction.Init)
            viewModel.sideEffect.collect {
                when (it) {
                    TopSideEffect.Error -> {
                        snackbarHostState.showSnackbar("エラー")
                    }
                    is TopSideEffect.Load -> {
                        loading = it.loading
                    }
                }
            }
        }
    }

    Scaffold(scaffoldState = scaffoldState) {
        Box {
            Text(text = state.news.articles.toString())
            if (loading) CircularProgressIndicator()
        }
    }
}
