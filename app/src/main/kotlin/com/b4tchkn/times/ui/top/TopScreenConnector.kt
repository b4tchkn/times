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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.b4tchkn.times.R
import com.b4tchkn.times.ui.Connector
import com.b4tchkn.times.ui.theme.AppColor
import com.b4tchkn.times.ui.top.model.TopAction

@Composable
fun TopScreenConnector(
    viewModel: TopStoreViewModel = viewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val snackbarHostState = scaffoldState.snackbarHostState

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar {
                Text(
                    text = stringResource(id = R.string.app_name),
                    color = AppColor.White,
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
