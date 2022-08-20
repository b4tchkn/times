package com.b4tchkn.times.data

import com.b4tchkn.times.model.GoogleNewsRssModel
import retrofit2.http.GET
import retrofit2.http.Path

interface GoogleNewsService {
    @GET("{topic}?hl=ja&gl=JP&ceid=JP:ja")
    suspend fun getTopicNews(@Path("topic") topic: String): GoogleNewsRssModel
}

enum class GoogleNewsServiceTopicType {
    WORLD,
    NATION,
    BUSINESS,
    TECHNOLOGY,
    ENTERTAINMENT,
    SPORTS,
    SCIENCE,
    HEALTH,
}
