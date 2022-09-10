package com.b4tchkn.times.di

import com.b4tchkn.times.data.GoogleNewsService
import com.b4tchkn.times.data.NewsApiService
import com.b4tchkn.times.data.OpenWeatherService
import com.b4tchkn.times.util.Constants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(90, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .build()

    @OptIn(ExperimentalSerializationApi::class)
    @JsonConverterFactory
    @Provides
    fun provideJsonConverterFactory(): Converter.Factory {
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
        val contentType = "application/json".toMediaType()
        return json.asConverterFactory(contentType)
    }

    @XmlConverterFactory
    @Provides
    fun provideXmlConverterFactory(): Converter.Factory = SimpleXmlConverterFactory.create()

    @Provides
    fun provideNewsApiService(
        okHttpClient: OkHttpClient,
        @JsonConverterFactory jsonConverterFactory: Converter.Factory,
    ): NewsApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.NEWS_API)
            .client(okHttpClient)
            .addConverterFactory(jsonConverterFactory)
            .build()
            .create(NewsApiService::class.java)
    }

    @Provides
    fun provideGoogleNewsService(
        okHttpClient: OkHttpClient,
        @XmlConverterFactory xmlConverterFactory: Converter.Factory,
    ): GoogleNewsService {
        return Retrofit.Builder()
            .baseUrl(Constants.GOOGLE_NEWS_API)
            .client(okHttpClient)
            .addConverterFactory(xmlConverterFactory)
            .build()
            .create(GoogleNewsService::class.java)
    }

    @Provides
    fun provideOpenWeatherService(
        okHttpClient: OkHttpClient,
        @JsonConverterFactory converterFactory: Converter.Factory,
    ): OpenWeatherService {
        return Retrofit.Builder()
            .baseUrl(Constants.OPEN_WEATHER_API)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
            .create()
    }
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class JsonConverterFactory

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class XmlConverterFactory
