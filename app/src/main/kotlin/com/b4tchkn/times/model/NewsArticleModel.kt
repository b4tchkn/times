package com.b4tchkn.times.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class NewsArticleModel(
    @JsonNames("source")
    val source: NewsSourceModel?,
    @JsonNames("author")
    val author: String?,
    @JsonNames("title")
    val title: String?,
    @JsonNames("description")
    val description: String?,
    @JsonNames("url")
    val url: String?,
    @JsonNames("urlToImage")
    val urlToImage: String?,
    @JsonNames("publishedAt")
    val publishedAt: String?,
    @JsonNames("content")
    val content: String?,
)
