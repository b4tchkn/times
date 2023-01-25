package com.b4tchkn.times.ui.screen.weather

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.b4tchkn.times.R
import com.b4tchkn.times.ui.Connector
import com.b4tchkn.times.ui.screen.weather.model.WeatherAction
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun WeatherScreenConnector(
    viewModel: WeatherStoreViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
) {
    val scaffoldState = rememberScaffoldState()
    val snackbarHostState = scaffoldState.snackbarHostState
    val context = LocalContext.current

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = "Weather")
            },
            navigationIcon = {
                IconButton(onClick = {
                    navigator.popBackStack()
                }) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = context.getString(R.string.semantics_back),
                    )
                }
            }
        )
    }) {
        Connector(
            snackbarHostState = snackbarHostState,
            storeViewModel = viewModel,
            initAction = WeatherAction.Init,
            initActionLoad = WeatherAction.InitLoad,
            refreshActionLoad = WeatherAction.RefreshLoad,
        ) { state ->
            WeatherScreen(
                paddingValues = it,
                state = state,
            )
        }
    }
}
