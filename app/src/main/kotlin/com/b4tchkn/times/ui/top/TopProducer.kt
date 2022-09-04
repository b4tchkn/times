package com.b4tchkn.times.ui.top

import com.b4tchkn.times.Producer
import com.b4tchkn.times.data.GoogleNewsServiceTopicType
import com.b4tchkn.times.domain.GetGoogleTopicNewsUseCase
import com.b4tchkn.times.ui.top.model.TopAction
import com.b4tchkn.times.ui.top.model.TopSideEffect
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class TopProducer @Inject constructor(
    private val getGoogleTopicNewsUseCase: GetGoogleTopicNewsUseCase,
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
        val news =
            getGoogleTopicNewsUseCase(topicType = GoogleNewsServiceTopicType.BUSINESS)
        return news.fold(
            onSuccess = {
                _sideEffect.emit(TopSideEffect.Load(loading = false))
                state.copy(news = it)
            },
            onFailure = {
                _sideEffect.emit(TopSideEffect.Load(loading = false))
                _sideEffect.emit(TopSideEffect.Error)
                state
            }
        )
    }
}
