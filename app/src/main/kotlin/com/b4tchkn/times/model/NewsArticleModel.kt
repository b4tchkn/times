package com.b4tchkn.times.model

import androidx.annotation.VisibleForTesting
import com.b4tchkn.times.model.response.NewsArticleResponse

data class NewsArticleModel(
    val source: NewsSourceModel,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?,
) {
    companion object {
        fun fromV1(response: NewsArticleResponse): NewsArticleModel {
            // val formatter = DateTimeFormatter.ofPattern("")
            // val publishedAt = LocalDateTime.parse(response.publishedAt, formatter)
            return NewsArticleModel(
                source = NewsSourceModel.fromV1(response.source),
                author = response.author,
                title = response.title,
                description = response.description,
                url = response.url,
                urlToImage = response.urlToImage,
                publishedAt = response.publishedAt,
                content = response.content,
            )
        }

        @VisibleForTesting
        val defaultInstance = NewsArticleModel(
            source = NewsSourceModel.defaultInstance,
            author = null,
            title = null,
            description = null,
            url = null,
            urlToImage = null,
            // publishedAt = LocalDateTime.parse(""),
            publishedAt = null,
            content = null,
        )
    }
}
