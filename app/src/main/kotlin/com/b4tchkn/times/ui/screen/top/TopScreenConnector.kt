package com.b4tchkn.times.ui.screen.top

import android.Manifest
import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.b4tchkn.times.ui.Connector
import com.b4tchkn.times.ui.screen.destinations.SearchScreenConnectorDestination
import com.b4tchkn.times.ui.screen.destinations.WeatherScreenConnectorDestination
import com.b4tchkn.times.ui.screen.destinations.WebViewScreenDestination
import com.b4tchkn.times.ui.screen.top.model.TopAction
import com.b4tchkn.times.util.urlFromHtmlTag
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SuppressLint("MissingPermission")
@OptIn(ExperimentalPermissionsApi::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun TopScreenConnector(
    viewModel: TopStoreViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
) {
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val snackbarHostState = scaffoldState.snackbarHostState
    val locationPermissionState = rememberPermissionState(
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    LaunchedEffect(Unit) {
        if (!locationPermissionState.status.isGranted) {
            locationPermissionState.launchPermissionRequest()
        }
    }

    LaunchedEffect(key1 = locationPermissionState.status.isGranted) {
        if (locationPermissionState.status.isGranted) {
            val location = fusedLocationClient.lastLocation
            location.addOnCompleteListener {
                val result = it.result
                viewModel.dispatch(
                    TopAction.UpdateLocation(
                        location = Pair(
                            first = result.latitude,
                            second = result.longitude,
                        )
                    )
                )
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
    ) {
        Connector(
            snackbarHostState = snackbarHostState,
            storeViewModel = viewModel,
            initAction = TopAction.Init,
            initActionLoad = TopAction.InitLoad,
            refreshActionLoad = TopAction.RefreshLoad,
        ) { state ->
            TopScreen(
                paddingValues = it,
                topState = state,
                onRefreshed = {
                    viewModel.dispatch(TopAction.RefreshLoad)
                    viewModel.dispatch(TopAction.Refresh)
                },
                onArticleHeaderClicked = {
                    navigator.navigate(
                        WebViewScreenDestination(
                            title = it.title!!,
                            url = it.url!!,
                        )
                    )
                },
                onSearchClicked = {
                    navigator.navigate(SearchScreenConnectorDestination)
                },
                onWeatherContentClicked = {
                    navigator.navigate(WeatherScreenConnectorDestination)
                },
                onHeadlineArticleClicked = {
                    navigator.navigate(
                        WebViewScreenDestination(
                            title = it.title!!,
                            url = it.url!!,
                        )
                    )
                },
                onCategoryTopicArticleClicked = {
                    navigator.navigate(
                        WebViewScreenDestination(
                            title = it.title,
                            url = urlFromHtmlTag(it.description),
                        ),
                    )
                },
            )
        }
    }
}
