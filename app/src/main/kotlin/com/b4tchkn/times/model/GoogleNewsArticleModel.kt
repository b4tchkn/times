package com.b4tchkn.times.model

import androidx.annotation.VisibleForTesting
import com.b4tchkn.times.model.response.GoogleNewsArticleResponse
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class GoogleNewsArticleModel(
    val title: String,
    val link: String,
    val publishedAt: LocalDateTime,
    val description: String,
    val source: String,
) {
    companion object {
        fun fromV1(response: GoogleNewsArticleResponse): GoogleNewsArticleModel {
            val formatter = DateTimeFormatter.RFC_1123_DATE_TIME
            val publishedAt = LocalDateTime.parse(response.pubDate, formatter)
            return GoogleNewsArticleModel(
                title = response.title,
                link = response.title,
                publishedAt = publishedAt,
                description = response.description,
                source = response.source,
            )
        }

        @VisibleForTesting
        val defaultInstance = GoogleNewsArticleModel(
            title = "",
            link = "",
            publishedAt = LocalDateTime.now(),
            description = "",
            source = "",
        )
    }
}
