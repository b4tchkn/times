package com.b4tchkn.times.model.response

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class NewsResponse(
    @JsonNames("totalResults")
    val totalResults: Int,
    @JsonNames("articles")
    val articles: List<NewsArticleResponse>,
) {
    companion object {
        val defaultInstance = NewsResponse(
            totalResults = 0,
            articles = listOf(),
        )
    }
}
