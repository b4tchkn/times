package com.b4tchkn.times.model

import androidx.annotation.VisibleForTesting
import com.b4tchkn.times.model.response.NewsArticleResponse
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

data class NewsArticleModel(
    val source: NewsSourceModel,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: LocalDateTime,
    val content: String?,
) {
    companion object {
        fun fromV1(response: NewsArticleResponse): NewsArticleModel {
            val formatter = DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.of("UTC"))
            val publishedAt = ZonedDateTime.parse(response.publishedAt, formatter).toLocalDateTime()
            return NewsArticleModel(
                source = NewsSourceModel.fromV1(response.source),
                author = response.author,
                title = response.title,
                description = response.description,
                url = response.url,
                urlToImage = response.urlToImage,
                publishedAt = publishedAt,
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
            publishedAt = LocalDateTime.now(),
            content = null,
        )
    }
}
