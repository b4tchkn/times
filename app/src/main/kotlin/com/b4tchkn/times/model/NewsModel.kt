package com.b4tchkn.times.model

import androidx.annotation.VisibleForTesting
import com.b4tchkn.times.model.response.NewsResponse

data class NewsModel(
    val totalResults: Int,
    val articles: List<NewsArticleModel>,
) {
    companion object {
        fun fromV1(response: NewsResponse) = NewsModel(
            totalResults = response.totalResults,
            articles = response.articles.map { NewsArticleModel.fromV1(it) }
        )

        @VisibleForTesting
        val defaultInstance = NewsModel(
            totalResults = 0,
            articles = listOf(),
        )
    }
}
