package com.b4tchkn.times.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.b4tchkn.times.Constants
import com.b4tchkn.times.data.GoogleNewsService
import com.b4tchkn.times.data.GoogleNewsServiceTopicType
import com.b4tchkn.times.data.NewsAPIService
import com.b4tchkn.times.model.GoogleNewsRssModel
import com.b4tchkn.times.model.NewsModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class HomeViewModel : ViewModel() {

    private val _news = MutableStateFlow<NewsModel?>(null)
    val news: StateFlow<NewsModel?>
        get() = _news

    private val _newsTopHeadlines = MutableStateFlow<NewsModel?>(null)
    val newsTopHeadlines: StateFlow<NewsModel?>
        get() = _newsTopHeadlines

    private val _googleNews = MutableStateFlow<GoogleNewsRssModel?>(null)
    val googleNews: StateFlow<GoogleNewsRssModel?>
        get() = _googleNews

    suspend fun fetchNewsEverything() {
        val contentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.NEWS_API)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()

        val newsService = retrofit.create(NewsAPIService::class.java)

        viewModelScope.launch {
            try {
                _news.value = newsService.getEverything()
            } catch (e: Exception) {
            }
        }
    }

    suspend fun fetchNewsTopHeadlines() {
        val contentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.NEWS_API)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()

        val newsService = retrofit.create(NewsAPIService::class.java)

        viewModelScope.launch {
            try {
                _newsTopHeadlines.value = newsService.getTopHeadlines()
            } catch (e: Exception) {
            }
        }
    }

    suspend fun fetchGoogleNews() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.GOOGLE_NEWS_API)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()

        val googleNewsService = retrofit.create(GoogleNewsService::class.java)

        viewModelScope.launch {
            try {
                _googleNews.value =
                    googleNewsService.getTopicNews(topic = GoogleNewsServiceTopicType.WORLD.name)
            } catch (e: Exception) {
            }
        }
    }
}
