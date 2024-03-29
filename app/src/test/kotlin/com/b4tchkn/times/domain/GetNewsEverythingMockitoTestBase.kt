package com.b4tchkn.times.domain

import com.b4tchkn.times.MockitoTestBase
import com.b4tchkn.times.data.NewsApiService
import com.b4tchkn.times.model.NewsModel
import com.b4tchkn.times.model.response.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class GetNewsEverythingMockitoTestBase : MockitoTestBase() {
    @Mock
    lateinit var newsApiService: NewsApiService
    lateinit var getNewsEverythingUseCase: GetNewsEverythingUseCase

    @Before
    fun setup() {
        getNewsEverythingUseCase = GetNewsEverythingUseCase(
            newsApiService,
            Dispatchers.IO,
        )
    }

    @Test
    fun useCase_Success() = runTest {
        whenever(newsApiService.getEverything())
            .thenReturn(NewsResponse.defaultInstance)

        val response = getNewsEverythingUseCase()

        val model = NewsModel.defaultInstance
        Assert.assertEquals(
            flowOf(Result.success(model)).first(),
            response.first(),
        )
    }

    @Test
    fun useCase_Failure() = runTest {
        val exception = RuntimeException()
        doThrow(exception).whenever(newsApiService).getEverything()

        val response = getNewsEverythingUseCase()

        Assert.assertEquals(
            flowOf(Result.failure<Exception>(exception)).first(),
            response.first(),
        )
    }
}
