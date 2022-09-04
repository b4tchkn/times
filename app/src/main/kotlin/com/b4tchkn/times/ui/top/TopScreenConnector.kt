package com.b4tchkn.times.ui.top

import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.b4tchkn.times.R
import com.b4tchkn.times.ui.top.model.TopAction
import com.b4tchkn.times.ui.top.model.TopSideEffect

@Composable
fun TopScreenConnector(
    viewModel: TopStoreViewModel = viewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val state by viewModel.uiState.collectAsState()
    var loading by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val snackbarHostState = scaffoldState.snackbarHostState

    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            viewModel.dispatch(TopAction.Init)
            viewModel.sideEffect.collect {
                when (it) {
                    TopSideEffect.Error -> {
                        snackbarHostState.showSnackbar(
                            message = context.getString(
                                R.string.snackbar_error
                            )
                        )
                    }
                    is TopSideEffect.Load -> {
                        loading = it.loading
                    }
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar {
                Text(
                    text = stringResource(id = R.string.app_name),
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.weight(1.0f))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = stringResource(id = R.string.semantics_search),
                    )
                }
            }
        },
    ) {
        TopScreen(
            modifier = Modifier,
            paddingValues = it,
            topState = state,
            loading = loading,
        )
    }
}
