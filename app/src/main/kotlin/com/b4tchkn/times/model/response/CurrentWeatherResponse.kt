package com.b4tchkn.times.model.response

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class CurrentWeatherResponse(
    @JsonNames("coord")
    val coord: CoordResponse,

    @JsonNames("weather")
    val weather: List<WeatherResponse>,

    @JsonNames("main")
    val main: MainResponse,
) {

    @Serializable
    data class CoordResponse(
        @JsonNames("lon")
        val longitude: Double,

        @JsonNames("lat")
        val latitude: Double,
    ) {
        companion object {
            val defaultInstance = CoordResponse(
                longitude = 0.0,
                latitude = 0.0,
            )
        }
    }

    @Serializable
    data class WeatherResponse(
        @JsonNames("id")
        val id: Int,

        @JsonNames("main")
        val main: String,

        @JsonNames("description")
        val description: String,

        @JsonNames("icon")
        val icon: String,
    ) {
        companion object {
            val defaultInstance = WeatherResponse(
                id = 0,
                main = "",
                description = "",
                icon = "",
            )
        }
    }

    @Serializable
    data class MainResponse(
        @JsonNames("temp")
        val temp: Double,

        @JsonNames("feels_like")
        val feelsLike: Double,

        @JsonNames("temp_min")
        val tempMin: Double,

        @JsonNames("temp_max")
        val tempMax: Double,

        @JsonNames("pressure")
        val pressure: Int,

        @JsonNames("humidity")
        val humidity: Int,
    ) {

        companion object {
            val defaultInstance = MainResponse(
                temp = 0.0,
                feelsLike = 0.0,
                tempMin = 0.0,
                tempMax = 0.0,
                pressure = 0,
                humidity = 0,
            )
        }
    }

    companion object {
        val defaultInstance = CurrentWeatherResponse(
            coord = CoordResponse.defaultInstance,
            weather = listOf(),
            main = MainResponse.defaultInstance,
        )
    }
}
