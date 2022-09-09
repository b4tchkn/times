package com.b4tchkn.times.model

import androidx.annotation.VisibleForTesting
import com.b4tchkn.times.model.response.GoogleNewsRssResponse

data class GoogleNewsRssModel(
    val title: String,
    val articles: List<GoogleNewsArticleModel>
) {
    companion object {
        fun fromV1(response: GoogleNewsRssResponse) = GoogleNewsRssModel(
            title = response.title,
            articles = response.articles.map { GoogleNewsArticleModel.fromV1(it) }
        )

        @VisibleForTesting
        val defaultInstance = GoogleNewsRssModel(
            title = "",
            articles = listOf()
        )
    }
}
