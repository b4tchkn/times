package com.b4tchkn.times.ui

import com.b4tchkn.times.MockitoTestBase
import com.b4tchkn.times.domain.GetCurrentWeatherUseCase
import com.b4tchkn.times.domain.GetGoogleTopicNewsUseCase
import com.b4tchkn.times.domain.GetNewsTopHeadlinesUseCase
import com.b4tchkn.times.model.CurrentWeatherModel
import com.b4tchkn.times.model.GoogleNewsRssModel
import com.b4tchkn.times.model.GoogleNewsServiceTopicTypeModel
import com.b4tchkn.times.model.NewsModel
import com.b4tchkn.times.ui.screen.top.TopProducer
import com.b4tchkn.times.ui.screen.top.TopUiState
import com.b4tchkn.times.ui.screen.top.model.TopAction
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class TopProducerTestBase : MockitoTestBase() {
    @Mock
    lateinit var getGoogleTopicNewsUseCase: GetGoogleTopicNewsUseCase

    @Mock
    lateinit var getNewsTopHeadlinesUseCase: GetNewsTopHeadlinesUseCase

    @Mock
    lateinit var getCurrentWeatherUseCase: GetCurrentWeatherUseCase

    private lateinit var topProducer: TopProducer

    @Before
    fun setup() {
        topProducer = TopProducer(
            getGoogleTopicNewsUseCase,
            getNewsTopHeadlinesUseCase,
            getCurrentWeatherUseCase,
        )
    }

    @Test
    fun producer_action_init() = runTest {
        val action = TopAction.Init
        val googleNewsModel = GoogleNewsRssModel.defaultInstance
        val topHeadlinesModel = NewsModel.defaultInstance
        val currentWeatherModel = CurrentWeatherModel.defaultInstance
        whenever(getGoogleTopicNewsUseCase(any()))
            .thenReturn(flowOf(Result.success(googleNewsModel)))
        whenever(getNewsTopHeadlinesUseCase()).thenReturn(flowOf(Result.success(topHeadlinesModel)))
        whenever(
            getCurrentWeatherUseCase(any(), any())
        ).thenReturn(flowOf(Result.success(currentWeatherModel)))
        val currentState = TopUiState(
            googleTopicNews = listOf(),
            topHeadlines = topHeadlinesModel,
            currentWeather = currentWeatherModel,
            location = null,
            error = false,
            loadingStatus = LoadingStatus.Init(loading = false)
        )
        val newState = topProducer.reduce(currentState, action)

        Assert.assertEquals(
            TopUiState(
                googleTopicNews =
                List(GoogleNewsServiceTopicTypeModel.values().size) { googleNewsModel },
                topHeadlines = topHeadlinesModel,
                currentWeather = currentWeatherModel,
                location = null,
                error = false,
                loadingStatus = LoadingStatus.Init(loading = false)
            ),
            newState,
        )
    }
}
