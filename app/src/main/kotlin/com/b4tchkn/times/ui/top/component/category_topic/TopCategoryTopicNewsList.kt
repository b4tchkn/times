package com.b4tchkn.times.ui.top.component.category_topic

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.b4tchkn.times.model.GoogleNewsArticleModel
import com.b4tchkn.times.ui.top.component.TopArticleItem

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
        }
    }
}

private const val maxArticleSize = 6