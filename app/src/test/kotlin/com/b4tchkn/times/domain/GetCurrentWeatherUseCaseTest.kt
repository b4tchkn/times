package com.b4tchkn.times.domain

import com.b4tchkn.times.UseCaseTest
import com.b4tchkn.times.data.OpenWeatherService
import com.b4tchkn.times.model.CurrentWeatherModel
import com.b4tchkn.times.model.response.CurrentWeatherResponse
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
class GetCurrentWeatherUseCaseTest : UseCaseTest() {
    @Mock
    lateinit var openWeatherService: OpenWeatherService
    lateinit var getCurrentWeatherUseCase: GetCurrentWeatherUseCase

    @Before
    fun setup() {
        getCurrentWeatherUseCase = GetCurrentWeatherUseCase(
            openWeatherService,
            Dispatchers.IO,
        )
    }

    @Test
    fun useCase_Success() = runTest {
        whenever(openWeatherService.getCurrentWeather(any(), any()))
            .thenReturn(CurrentWeatherResponse.defaultInstance)

        val response = getCurrentWeatherUseCase(0.0, 0.0)

        val model = CurrentWeatherModel.defaultInstance
        Assert.assertEquals(
            flowOf(Result.success(model)).first(),
            response.first(),
        )
    }

    @Test
    fun useCase_Failure() = runTest {
        val exception = RuntimeException()
        doThrow(exception).whenever(openWeatherService).getCurrentWeather(any(), any())

        val response = getCurrentWeatherUseCase(0.0, 0.0)

        Assert.assertEquals(
            flowOf(Result.failure<Exception>(exception)).first(),
            response.first()
        )
    }
}
