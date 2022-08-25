package com.b4tchkn.times.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.b4tchkn.times.data.GoogleNewsService
import com.b4tchkn.times.data.GoogleNewsServiceTopicType
import com.b4tchkn.times.data.NewsApiService
import com.b4tchkn.times.model.GoogleNewsRssModel
import com.b4tchkn.times.model.NewsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsApiService: NewsApiService,
    private val googleNewsService: GoogleNewsService,
) : ViewModel() {

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
        viewModelScope.launch {
            try {
                _news.value = newsApiService.getEverything()
            } catch (e: Exception) {
            }
        }
    }

    suspend fun fetchNewsTopHeadlines() {
        viewModelScope.launch {
            try {
                _newsTopHeadlines.value = newsApiService.getTopHeadlines()
            } catch (e: Exception) {
            }
        }
    }

    suspend fun fetchGoogleNews() {
        viewModelScope.launch {
            try {
                _googleNews.value =
                    googleNewsService.getTopicNews(topic = GoogleNewsServiceTopicType.WORLD.name)
            } catch (e: Exception) {
            }
        }
    }
}
