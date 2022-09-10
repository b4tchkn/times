package com.b4tchkn.times.data

import com.b4tchkn.times.BuildConfig
import com.b4tchkn.times.model.response.CurrentWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {
    @GET("weather?appid=${BuildConfig.openWeatherApiKey}&lang=ja&units=metric")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
    ): CurrentWeatherResponse
}
