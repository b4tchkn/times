package com.b4tchkn.times.domain

import com.b4tchkn.times.data.OpenWeatherService
import com.b4tchkn.times.di.IODispatcher
import com.b4tchkn.times.model.CurrentWeatherModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetCurrentWeatherUseCase @Inject constructor(
    private val openWeatherService: OpenWeatherService,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(
        latitude: Double,
        longitude: Double,
    ) = flow {
        val response = openWeatherService.getCurrentWeather(
            lat = latitude,
            lon = longitude,
        )
        val model = CurrentWeatherModel.fromV1(response)
        emit(Result.success(model))
    }
        .catch { emit(Result.failure(it)) }
        .flowOn(ioDispatcher)
}
