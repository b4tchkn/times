package com.b4tchkn.times.model

import androidx.annotation.VisibleForTesting
import com.b4tchkn.times.model.response.GoogleNewsArticleResponse

data class GoogleNewsArticleModel(
    val title: String,
    val link: String,
    val publishedAt: String,
    val description: String,
    val source: String,
) {
    companion object {
        fun fromV1(response: GoogleNewsArticleResponse): GoogleNewsArticleModel {
            // val formatter = DateTimeFormatter.ofPattern("")
            // val publishedAt = LocalDateTime.parse(response.pubDate, formatter)
            return GoogleNewsArticleModel(
                title = response.title,
                link = response.title,
                publishedAt = response.pubDate,
                description = response.description,
                source = response.source,
            )
        }

        @VisibleForTesting
        val defaultInstance = GoogleNewsArticleModel(
            title = "",
            link = "",
            // publishedAt = LocalDateTime.parse(""),
            publishedAt = "",
            description = "",
            source = "",
        )
    }
}
