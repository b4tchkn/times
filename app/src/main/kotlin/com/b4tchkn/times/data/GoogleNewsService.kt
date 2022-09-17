package com.b4tchkn.times.data

import com.b4tchkn.times.model.response.GoogleNewsRssResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GoogleNewsService {
    @GET("{topic}?hl=ja&gl=JP&ceid=JP:ja")
    suspend fun getTopicNews(@Path("topic") topic: String): GoogleNewsRssResponse
}
