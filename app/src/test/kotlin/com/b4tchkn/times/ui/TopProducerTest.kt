package com.b4tchkn.times.ui

import com.b4tchkn.times.UseCaseTest
import com.b4tchkn.times.domain.GetGoogleTopicNewsUseCase
import com.b4tchkn.times.domain.GetNewsTopHeadlinesUseCase
import com.b4tchkn.times.model.GoogleNewsRssModel
import com.b4tchkn.times.model.NewsModel
import com.b4tchkn.times.ui.top.TopProducer
import com.b4tchkn.times.ui.top.TopState
import com.b4tchkn.times.ui.top.model.TopAction
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
class TopProducerTest : UseCaseTest() {
    @Mock
    lateinit var getGoogleTopicNewsUseCase: GetGoogleTopicNewsUseCase

    @Mock
    lateinit var getNewsTopHeadlinesUseCase: GetNewsTopHeadlinesUseCase

    lateinit var topProducer: TopProducer

    @Before
    fun setup() {
        topProducer = TopProducer(
            getGoogleTopicNewsUseCase,
            getNewsTopHeadlinesUseCase,
        )
    }

    @Test
    fun producer_action_init() = runTest {
        val action = TopAction.Init
        val googleNewsModel = GoogleNewsRssModel.defaultInstance
        val topHeadlinesModel = NewsModel.defaultInstance
        whenever(getGoogleTopicNewsUseCase(any()))
            .thenReturn(flowOf(Result.success(googleNewsModel)))
        whenever(getNewsTopHeadlinesUseCase()).thenReturn(flowOf(Result.success(topHeadlinesModel)))
        val currentState = TopState(googleNews = googleNewsModel, topHeadlines = topHeadlinesModel)
        val newState = topProducer.reduce(currentState, action)

        Assert.assertEquals(
            TopState(googleNews = googleNewsModel, topHeadlines = topHeadlinesModel), newState,
        )
    }
}