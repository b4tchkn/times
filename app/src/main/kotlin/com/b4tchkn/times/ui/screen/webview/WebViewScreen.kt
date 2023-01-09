package com.b4tchkn.times.ui.screen.webview

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.b4tchkn.times.R
import com.b4tchkn.times.ui.component.LoadIndicator
import com.b4tchkn.times.ui.theme.AppColor
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SuppressLint("SetJavaScriptEnabled")
@Destination
@Composable
fun WebViewScreen(
    title: String,
    url: String,
    navigator: DestinationsNavigator,
) {
    val context = LocalContext.current
    var loading by rememberSaveable { mutableStateOf(true) }
    var hasError by rememberSaveable { mutableStateOf(false) }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                )
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
        AndroidView(
            modifier = Modifier.padding(top = it.calculateTopPadding()),
            factory = ::WebView,
            update = { webView ->
                webView.webViewClient = AppWebViewClient(
                    onPageStarted = {
                        loading = false
                    },
                    onReceivedError = {
                        hasError = true
                    },
                )
                webView.loadUrl(url)
                webView.settings.apply {
                    javaScriptEnabled = true
                }
            }
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding()),
            contentAlignment = Alignment.Center,
        ) {
            if (loading) LoadIndicator()
        }
        if (hasError)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppColor.DarkGrey),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = context.getString(
                        R.string.error_webView,
                    ),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                )
            }
    }
}

class AppWebViewClient(
    val onPageStarted: () -> Unit,
    val onReceivedError: () -> Unit,
) :
    WebViewClient() {

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        onPageStarted()
        super.onPageStarted(view, url, favicon)
    }

    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        onReceivedError()
        super.onReceivedError(view, request, error)
    }
}
