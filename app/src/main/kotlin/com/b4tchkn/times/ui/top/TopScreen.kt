package com.b4tchkn.times.ui.top

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.b4tchkn.times.ui.CommonSideEffect
import com.b4tchkn.times.ui.LoadingStatus
import com.b4tchkn.times.ui.component.Gap
import com.b4tchkn.times.ui.top.component.TopArticleHeader
import com.b4tchkn.times.ui.top.component.TopHeaderOverRow
import com.b4tchkn.times.ui.top.component.category_topic.TopCategoryTopicNews
import com.b4tchkn.times.ui.top.component.headlines_carousel.TopHeadlinesCarousel
import com.b4tchkn.times.ui.top.model.TopSideEffect
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun TopScreen(
    paddingValues: PaddingValues,
    topState: TopState,
    effect: SharedFlow<TopSideEffect>,
    onRefreshed: () -> Unit,
) {
    val googleCategoryTopics = topState.googleTopicNews
    val topHeadlines = topState.topHeadlines?.articles
    val currentWeather = topState.currentWeather
    var loading by remember { mutableStateOf(false) }
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(key1 = effect) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            effect.collect {
                println("🐝🐝🐝 TopScreen - TopSideEffect: $it")

                if (it is TopSideEffect.Common && it.commonSideEffect is CommonSideEffect.Load) {
                    when (it.commonSideEffect.loadingStatus) {
                        is LoadingStatus.Init -> {
                            // do nothing
                        }
                        is LoadingStatus.Refresh -> {
                            println("🐝🐝🐝 ローディング${it.commonSideEffect.loadingStatus.loading}")
                            loading =
                                it.commonSideEffect.loadingStatus.loading
                        }
                    }
                }
            }
        }
    }

    println("リフレッシュ $loading")

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = loading),
        onRefresh = {
            onRefreshed()
        }
    ) {
        Box(
            modifier = Modifier
                .padding(
                    top = paddingValues.calculateTopPadding(),
                ),
        ) {
            LazyColumn {
                item {
                    val article = topHeadlines?.firstOrNull() ?: return@item
                    Box(
                        contentAlignment = Alignment.TopCenter
                    ) {
                        TopArticleHeader(article = article) {
                            // TODO: handle callback
                        }
                        TopHeaderOverRow(
                            currentWeather = currentWeather,
                            onWeatherClicked = {
                                // TODO: handle callback
                            },
                            onSearchClicked = {
                                // TODO: handle callback
                            },
                        )
                    }
                }
                item {
                    Gap(32.dp)
                }
                if (topHeadlines != null) {
                    item {
                        TopHeadlinesCarousel(
                            headlines = topHeadlines,
                            onArticleCardClicked = {
                                // TODO: handle callback
                            }
                        )
                    }
                    item {
                        Gap(padding = 16.dp)
                    }
                }
                if (googleCategoryTopics.isNotEmpty()) {
                    item {
                        TopCategoryTopicNews(
                            googleCategoryTopicNews = googleCategoryTopics,
                            onArticleClicked = {},
                        )
                    }
                    item {
                        Gap(padding = 16.dp)
                    }
                }
            }
        }
    }
}
