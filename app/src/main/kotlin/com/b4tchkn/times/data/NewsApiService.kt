package com.b4tchkn.times.data

import com.b4tchkn.times.BuildConfig
import com.b4tchkn.times.model.NewsModel
import retrofit2.http.GET

interface NewsApiService {
    @GET("top-headlines?apiKey=${BuildConfig.newsApiKey}&language=jp")
    suspend fun getTopHeadlines(): NewsModel

    @GET("everything?q=tesla&apiKey=${BuildConfig.newsApiKey}&language=jp")
    suspend fun getEverything(): NewsModel
}
