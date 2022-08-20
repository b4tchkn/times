package com.b4tchkn.times.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.b4tchkn.times.Constants
import com.b4tchkn.times.data.GoogleNewsService
import com.b4tchkn.times.data.GoogleNewsServiceTopicType
import com.b4tchkn.times.model.GoogleNewsRssModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class HomeViewModel : ViewModel() {

    private val _news = MutableStateFlow<GoogleNewsRssModel?>(null)
    val news: StateFlow<GoogleNewsRssModel?>
        get() = _news

    suspend fun fetch() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.GOOGLE_NEWS_API)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()

        val googleNewsService = retrofit.create(GoogleNewsService::class.java)

        viewModelScope.launch {
            _news.value =
                googleNewsService.getTopicNews(topic = GoogleNewsServiceTopicType.BUSINESS.name)
        }
    }
}
