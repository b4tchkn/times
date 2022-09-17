package com.b4tchkn.times.ui.top

import androidx.lifecycle.viewModelScope
import com.b4tchkn.times.model.CurrentWeatherModel
import com.b4tchkn.times.model.GoogleNewsRssModel
import com.b4tchkn.times.model.NewsModel
import com.b4tchkn.times.model.State
import com.b4tchkn.times.model.StoreViewModel
import com.b4tchkn.times.ui.top.model.TopAction
import com.b4tchkn.times.ui.top.model.TopSideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class TopStoreViewModel @Inject constructor(
    private val producer: TopProducer,
) :
    StoreViewModel<TopAction, TopState, TopSideEffect>() {
    private val _uiState = MutableStateFlow(TopState.init)
    override val uiState: StateFlow<TopState>
        get() = _uiState.asStateFlow()

    override val sideEffect: SharedFlow<TopSideEffect>
        get() = producer.sideEffect

    override fun dispatch(action: TopAction) {
        viewModelScope.launch {
            _uiState.value = producer.reduce(_uiState.value, action)
        }
    }
}

data class TopState(
    val googleTopicNews: List<GoogleNewsRssModel>,
    val topHeadlines: NewsModel?,
    val currentWeather: CurrentWeatherModel?,
    override val error: Boolean,
) : State() {
    companion object {
        val init = TopState(
            googleTopicNews = listOf(),
            topHeadlines = null,
            currentWeather = null,
            error = false,
        )
    }
}
