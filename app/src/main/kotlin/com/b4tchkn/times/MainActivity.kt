package com.b4tchkn.times

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.b4tchkn.times.ui.screen.NavGraphs
import com.b4tchkn.times.ui.screen.destinations.SearchScreenConnectorDestination
import com.b4tchkn.times.ui.screen.destinations.TopScreenConnectorDestination
import com.b4tchkn.times.ui.screen.destinations.WeatherScreenConnectorDestination
import com.b4tchkn.times.ui.screen.search.SearchScreenConnector
import com.b4tchkn.times.ui.screen.top.TopScreenConnector
import com.b4tchkn.times.ui.screen.weather.WeatherScreenConnector
import com.b4tchkn.times.ui.theme.TimesTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.manualcomposablecalls.ManualComposableCallsBuilder
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DestinationsNavHost(
                        navGraph = NavGraphs.root,
                        startRoute = NavGraphs.root.startRoute,
                    ) {
                        composableGroup()
                    }
                }
            }
        }
    }
}

private fun ManualComposableCallsBuilder.composableGroup() {
    composable(TopScreenConnectorDestination) {
        TopScreenConnector(
            navigator = destinationsNavigator,
        )
    }
    composable(SearchScreenConnectorDestination) {
        SearchScreenConnector()
    }
    composable(WeatherScreenConnectorDestination) {
        WeatherScreenConnector()
    }
}
