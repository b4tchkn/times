package com.b4tchkn.times.ui.top.component.category_topic

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import com.b4tchkn.times.model.GoogleNewsArticleModel
import com.b4tchkn.times.model.GoogleNewsRssModel
import com.b4tchkn.times.model.GoogleNewsServiceTopicTypeModel
import com.b4tchkn.times.ui.component.Gap
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TopCategoryTopicNews(
    googleCategoryTopicNews: List<GoogleNewsRssModel>,
    onArticleClicked: (article: GoogleNewsArticleModel) -> Unit,
) {
    val pagerState = rememberPagerState(0)
    val scope = rememberCoroutineScope()

    Column {
        TopCategoryTopicTab(
            selectedTabIndex = pagerState.currentPage,
            onTabClicked = {
                scope.launch {
                    pagerState.animateScrollToPage(it)
                }
            }
        )
        Gap(height = 8.dp)
        HorizontalPager(
            count = GoogleNewsServiceTopicTypeModel.values().size,
            state = pagerState,
        ) {
            TopCategoryTopicNewsList(
                categoryTopicArticles = googleCategoryTopicNews[it].articles,
                onArticleClicked = onArticleClicked,
            )
        }
    }
}
