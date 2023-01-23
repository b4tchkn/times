package com.b4tchkn.times.ui

import com.b4tchkn.times.MockitoTestBase
import com.b4tchkn.times.domain.GetCurrentWeatherUseCase
import com.b4tchkn.times.model.CurrentWeatherModel
import com.b4tchkn.times.ui.screen.weather.WeatherProducer
import com.b4tchkn.times.ui.screen.weather.WeatherUiState
import com.b4tchkn.times.ui.screen.weather.model.WeatherAction
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
class WeatherProducerTest : MockitoTestBase() {
    @Mock
    lateinit var getCurrentWeatherUseCase: GetCurrentWeatherUseCase

    private lateinit var weatherProducer: WeatherProducer

    @Before
    fun setup() {
        weatherProducer = WeatherProducer(getCurrentWeatherUseCase)
    }

    @Test
    fun producer_action_init() = runTest {
        val action = WeatherAction.Init
        val currentWeatherModel = CurrentWeatherModel.defaultInstance
        whenever(getCurrentWeatherUseCase(any(), any())).thenReturn(
            flowOf(
                Result.success(
                    currentWeatherModel
                )
            )
        )
        val currentState = WeatherUiState(
            currentWeather = currentWeatherModel,
            error = false,
            loadingStatus = LoadingStatus.Init(loading = false)
        )
        val newState = weatherProducer.reduce(currentState, action)

        Assert.assertEquals(
            WeatherUiState(
                currentWeather = currentWeatherModel,
                error = false,
                loadingStatus = LoadingStatus.Init(loading = false)
            ),
            newState,
        )
    }
}
