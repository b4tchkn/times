package com.b4tchkn.times.module

import com.b4tchkn.times.Constants
import com.b4tchkn.times.data.GoogleNewsService
import com.b4tchkn.times.data.NewsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideNewsApiService(): NewsApiService {
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(Constants.NEWS_API)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(NewsApiService::class.java)
    }

    @Provides
    fun provideGoogleNewsService(): GoogleNewsService {
        return Retrofit.Builder()
            .baseUrl(Constants.GOOGLE_NEWS_API)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
            .create(GoogleNewsService::class.java)
    }
}
