package com.b4tchkn.times.domain

import com.b4tchkn.times.UseCaseTest
import com.b4tchkn.times.data.GoogleNewsService
import com.b4tchkn.times.data.GoogleNewsServiceTopicType
import com.b4tchkn.times.model.GoogleNewsRssModel
import com.b4tchkn.times.model.response.GoogleNewsRssResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class GetGoogleTopicNewsUseCaseTest : UseCaseTest() {
    @Mock
    lateinit var googleNewsService: GoogleNewsService
    lateinit var getGoogleTopicNewsUseCase: GetGoogleTopicNewsUseCase

    @Before
    fun setup() {
        getGoogleTopicNewsUseCase = GetGoogleTopicNewsUseCase(
            googleNewsService,
            Dispatchers.IO,
        )
    }

    @Test
    fun useCase_Success() = runTest {
        whenever(googleNewsService.getTopicNews(any()))
            .thenReturn(GoogleNewsRssResponse.defaultInstance)

        val response = getGoogleTopicNewsUseCase(GoogleNewsServiceTopicType.BUSINESS)

        val model = GoogleNewsRssModel.defaultInstance
        Assert.assertEquals(
            flowOf(Result.success(model)).first(),
            response.first(),
        )
    }

    @Test
    fun useCase_Failure() = runTest {
        val exception = RuntimeException()
        doThrow(exception).whenever(googleNewsService)
            .getTopicNews(GoogleNewsServiceTopicType.BUSINESS.name)

        val response = getGoogleTopicNewsUseCase(GoogleNewsServiceTopicType.BUSINESS)

        Assert.assertEquals(
            flowOf(Result.failure<Exception>(exception)).first(),
            response.first(),
        )
    }
}
