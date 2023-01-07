package com.b4tchkn.times.ui.screen.webview

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun WebViewScreen(
    title: String,
    url: String,
) {
    Scaffold(topBar = {
        TopAppBar {
            Text(
                text = title,
                maxLines = 1,
            )
        }
    }) {
        AndroidView(
            modifier = Modifier.padding(top = it.calculateTopPadding()),
            factory = ::WebView,
            update = { webView ->
                webView.webViewClient = WebViewClient()
                webView.loadUrl(url)
            }
        )
    }
}
