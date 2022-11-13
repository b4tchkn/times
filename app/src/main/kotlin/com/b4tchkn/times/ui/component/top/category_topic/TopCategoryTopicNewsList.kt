package com.b4tchkn.times.ui.component.top.category_topic

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.b4tchkn.times.model.GoogleNewsArticleModel
import com.b4tchkn.times.ui.component.AppDivider
import com.b4tchkn.times.ui.component.top.TopArticleItem

@Composable
fun TopCategoryTopicNewsList(
    categoryTopicArticles: List<GoogleNewsArticleModel>,
    onArticleClicked: (article: GoogleNewsArticleModel) -> Unit,
) {
    val contentSize =
        if (categoryTopicArticles.size > maxArticleSize) maxArticleSize
        else categoryTopicArticles.size

    Column {
        repeat(contentSize) {
            val article = categoryTopicArticles[it]
            TopArticleItem(
                title = article.title,
                source = article.source,
                publishDate = article.publishedAt.toString(),
                onArticleClicked = { onArticleClicked(article) },
            )
            AppDivider(
                startIndent = 16.dp,
                endIndent = 16.dp,
                thickness = 2.dp,
            )
        }
    }
}

private const val maxArticleSize = 10
