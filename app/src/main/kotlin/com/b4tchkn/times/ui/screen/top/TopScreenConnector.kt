package com.b4tchkn.times.ui.screen.top

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.b4tchkn.times.ui.Connector
import com.b4tchkn.times.ui.screen.destinations.SearchScreenConnectorDestination
import com.b4tchkn.times.ui.screen.top.model.TopAction
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun TopScreenConnector(
    viewModel: TopStoreViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
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
                onArticleHeaderClicked = {},
                onSearchClicked = {
                    navigator.navigate(SearchScreenConnectorDestination)
                },
                onWeatherContentClicked = {},
                onHeadlineArticleClicked = {},
                onCategoryTopicArticleClicked = {},
            )
        }
    }
}
