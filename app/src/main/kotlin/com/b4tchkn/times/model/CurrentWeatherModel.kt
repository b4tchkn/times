package com.b4tchkn.times.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class CurrentWeatherModel(
    @JsonNames("coord")
    val coord: CoordModel,

    @JsonNames("weather")
    val weather: List<WeatherModel>,

    @JsonNames("main")
    val main: MainModel,
) {

    @Serializable
    data class CoordModel(
        @JsonNames("lon")
        val longitude: Double,

        @JsonNames("lat")
        val latitude: Double,
    ) {
        companion object {
            val defaultInstance = CoordModel(
                longitude = 0.0,
                latitude = 0.0,
            )
        }
    }

    @Serializable
    data class WeatherModel(
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
            val defaultInstance = WeatherModel(
                id = 0,
                main = "",
                description = "",
                icon = "",
            )
        }
    }

    @Serializable
    data class MainModel(
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
            val defaultInstance = MainModel(
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
        val defaultInstance = CurrentWeatherModel(
            coord = CoordModel.defaultInstance,
            weather = listOf(),
            main = MainModel.defaultInstance,
        )
    }
}
