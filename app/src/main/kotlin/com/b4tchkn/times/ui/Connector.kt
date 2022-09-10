package com.b4tchkn.times.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.b4tchkn.times.ui.component.ErrorScreen
import com.b4tchkn.times.ui.component.LoadIndicator

@Composable
fun Connector(
    modifier: Modifier = Modifier.fillMaxSize(),
    loadingStatus: LoadingStatus?,
    error: Boolean,
    screen: @Composable () -> Unit,
) {
    if (loadingStatus == null ||
        (loadingStatus is LoadingStatus.Init && loadingStatus.loading)
    ) {
        LoadIndicator()
        return
    }


    if (error) {
        ErrorScreen(modifier = modifier)
    } else {
        Box(modifier = modifier) {
            screen()
            if (loadingStatus is LoadingStatus.Refresh && loadingStatus.loading) {
                LoadIndicator()
            }
        }
    }
}