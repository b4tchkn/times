package com.b4tchkn.times.domain

import com.b4tchkn.times.UseCaseTest
import com.b4tchkn.times.data.GoogleNewsService
import com.b4tchkn.times.data.GoogleNewsServiceTopicType
import com.b4tchkn.times.model.GoogleNewsRssModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
        getGoogleTopicNewsUseCase = GetGoogleTopicNewsUseCase(googleNewsService)
    }

    @Test
    fun useCase_Success() = runTest {
        val model = GoogleNewsRssModel.defaultInstance
        whenever(googleNewsService.getTopicNews(any())).thenReturn(model)

        val response = getGoogleTopicNewsUseCase(GoogleNewsServiceTopicType.BUSINESS)

        Assert.assertEquals(
            Result.success(model),
            response,
        )
    }

    @Test
    fun useCase_Failure() = runTest {
        val exception = RuntimeException()
        doThrow(exception).whenever(googleNewsService)
            .getTopicNews(GoogleNewsServiceTopicType.BUSINESS.name)

        val response = getGoogleTopicNewsUseCase(GoogleNewsServiceTopicType.BUSINESS)

        Assert.assertEquals(
            Result.failure<Exception>(exception),
            response,
        )
    }
}
