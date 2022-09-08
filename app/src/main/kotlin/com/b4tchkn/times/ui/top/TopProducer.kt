package com.b4tchkn.times.ui.top

import com.b4tchkn.times.Producer
import com.b4tchkn.times.data.GoogleNewsServiceTopicType
import com.b4tchkn.times.domain.GetCurrentWeatherUseCase
import com.b4tchkn.times.domain.GetGoogleTopicNewsUseCase
import com.b4tchkn.times.domain.GetNewsTopHeadlinesUseCase
import com.b4tchkn.times.ui.top.model.TopAction
import com.b4tchkn.times.ui.top.model.TopSideEffect
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first

class TopProducer @Inject constructor(
    private val getGoogleTopicNewsUseCase: GetGoogleTopicNewsUseCase,
    private val getNewsTopHeadlinesUseCase: GetNewsTopHeadlinesUseCase,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
) : Producer<TopState, TopAction, TopSideEffect>() {
    private val _sideEffect = MutableSharedFlow<TopSideEffect>(replay = 1)
    override val sideEffect: SharedFlow<TopSideEffect>
        get() = _sideEffect.asSharedFlow()

    override suspend fun reduce(state: TopState, action: TopAction): TopState {
        return when (action) {
            is TopAction.Init -> fetch(state)
            is TopAction.Refresh -> fetch(state)
        }
    }

    private suspend fun fetch(state: TopState): TopState {
        _sideEffect.emit(TopSideEffect.Load(loading = true))
        val googleTopicNews =
            getGoogleTopicNewsUseCase(topicType = GoogleNewsServiceTopicType.BUSINESS)
        val topHeadlines = getNewsTopHeadlinesUseCase()
        val currentWeather = getCurrentWeatherUseCase(
            latitude = 35.658034,
            longitude = 139.701636,
        )

        return combine(
            googleTopicNews,
            topHeadlines,
            currentWeather
        ) { googleTopicNews, topHeadlines, currentWeather ->
            _sideEffect.emit(TopSideEffect.Load(loading = false))
            state.copy(
                googleNews = googleTopicNews.getOrNull(),
                topHeadlines = topHeadlines.getOrNull(),
                currentWeather = currentWeather.getOrNull(),
            )
        }.first()
    }
}
