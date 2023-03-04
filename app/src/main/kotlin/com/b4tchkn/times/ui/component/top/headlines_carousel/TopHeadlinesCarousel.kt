package com.b4tchkn.times.ui.component.top.headlines_carousel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.b4tchkn.times.R
import com.b4tchkn.times.model.NewsArticleModel
import com.b4tchkn.times.ui.component.Gap

@Composable
fun TopHeadlinesCarousel(
    headlines: List<NewsArticleModel>,
    onArticleCardClicked: (article: NewsArticleModel) -> Unit,
) {
    val maxHeadlinesCount = 6

    Column {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = stringResource(R.string.top_headlines_title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
        Gap(height = 8.dp)
        if (headlines.isNotEmpty())
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(maxHeadlinesCount) {
                    if (it != 0) {
                        TopHeadlinesCarouselArticleCard(
                            modifier = Modifier.padding(
                                end = if (it != maxHeadlinesCount - 1) 16.dp else 0.dp
                            ),
                            article = headlines[it],
                            onCardClicked = { onArticleCardClicked(headlines[it]) },
                        )
                    }
                }
            }
    }
}
