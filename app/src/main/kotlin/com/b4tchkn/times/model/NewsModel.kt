package com.b4tchkn.times.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class NewsModel(
    @JsonNames("totalResults")
    val totalResults: Int,
    @JsonNames("articles")
    val articles: List<NewsArticleModel>,
)
