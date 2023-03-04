package com.b4tchkn.times.ui.screen.top

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.b4tchkn.times.R
import com.b4tchkn.times.model.GoogleNewsArticleModel
import com.b4tchkn.times.model.NewsArticleModel
import com.b4tchkn.times.ui.component.Gap
import com.b4tchkn.times.ui.component.top.TopArticleHeader
import com.b4tchkn.times.ui.component.top.TopWeatherContent
import com.b4tchkn.times.ui.component.top.category_topic.TopCategoryTopicNews
import com.b4tchkn.times.ui.component.top.headlines_carousel.TopHeadlinesCarousel
import com.b4tchkn.times.ui.theme.AppColor
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun TopScreen(
    paddingValues: PaddingValues,
    topState: TopUiState,
    onRefreshed: () -> Unit,
    onArticleHeaderClicked: (article: NewsArticleModel) -> Unit,
    onSearchClicked: () -> Unit,
    onWeatherContentClicked: () -> Unit,
    onHeadlineArticleClicked: (article: NewsArticleModel) -> Unit,
    onCategoryTopicArticleClicked: (article: GoogleNewsArticleModel) -> Unit,
) {
    val googleCategoryTopics = topState.googleTopicNews
    val topHeadlines = topState.topHeadlines?.articles
    val currentWeather = topState.currentWeather

    SwipeRefresh(
        state = rememberSwipeRefreshState(
            // Connector側でLoadingIndicator出すため
            isRefreshing = false
        ),
        onRefresh = onRefreshed,
    ) {
        Box(
            modifier = Modifier.padding(
                top = paddingValues.calculateTopPadding(),
            ),
        ) {
            LazyColumn {
                item {
                    val article = topHeadlines?.firstOrNull() ?: return@item
                    Box(
                        contentAlignment = Alignment.TopEnd
                    ) {
                        TopArticleHeader(
                            article = article,
                            onClicked = onArticleHeaderClicked,
                        )
                        Box(
                            modifier = Modifier.padding(top = 16.dp, end = 16.dp)
                        ) {
                            Icon(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(AppColor.Grey)
                                    .clickable { onSearchClicked() }
                                    .padding(12.dp),
                                imageVector = Icons.Default.Search,
                                contentDescription = stringResource(id = R.string.semantics_search)
                            )
                        }
                    }
                }
                if (currentWeather != null) {
                    items(3) {
                        when (it) {
                            0 -> {
                                Gap(padding = 8.dp)
                            }
                            1 -> {
                                Box(
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                ) {
                                    TopWeatherContent(
                                        currentWeather = currentWeather,
                                        onClicked = onWeatherContentClicked,
                                    )
                                }
                            }
                            2 -> {
                                Gap(padding = 8.dp)
                            }
                        }
                    }
                }
                if (topHeadlines != null) {
                    item {
                        TopHeadlinesCarousel(
                            headlines = topHeadlines,
                            onArticleCardClicked = onHeadlineArticleClicked,
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
                            onArticleClicked = onCategoryTopicArticleClicked,
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
