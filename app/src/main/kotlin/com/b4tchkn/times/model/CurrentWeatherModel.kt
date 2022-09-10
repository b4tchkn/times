package com.b4tchkn.times.model

import androidx.annotation.VisibleForTesting
import com.b4tchkn.times.model.response.CurrentWeatherResponse

data class CurrentWeatherModel(
    val coord: CoordModel,
    val weather: WeatherModel?,
    val main: MainModel,
) {
    data class CoordModel(
        val longitude: Double,
        val latitude: Double,
    ) {
        companion object {
            fun fromV1(response: CurrentWeatherResponse.CoordResponse) = CoordModel(
                longitude = response.longitude,
                latitude = response.longitude,
            )

            @VisibleForTesting
            val defaultInstance = CoordModel(
                longitude = 0.0,
                latitude = 0.0,
            )
        }
    }

    data class WeatherModel(
        val id: Int,
        val main: String,
        val description: String,
        val icon: String,
    ) {
        companion object {
            fun fromV1(response: CurrentWeatherResponse.WeatherResponse?) =
                if (response == null) null else WeatherModel(
                    id = response.id,
                    main = response.main,
                    description = response.description,
                    icon = response.icon,
                )

            @VisibleForTesting
            val defaultInstance = WeatherModel(
                id = 0,
                main = "",
                description = "",
                icon = "",
            )
        }
    }

    data class MainModel(
        val temp: Double,
        val feelsLike: Double,
        val tempMin: Double,
        val tempMax: Double,
        val pressure: Int,
        val humidity: Int,
    ) {
        companion object {
            fun fromV1(response: CurrentWeatherResponse.MainResponse) = MainModel(
                temp = response.temp,
                feelsLike = response.feelsLike,
                tempMin = response.tempMin,
                tempMax = response.tempMax,
                pressure = response.pressure,
                humidity = response.humidity,
            )

            @VisibleForTesting
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
        fun fromV1(response: CurrentWeatherResponse) = CurrentWeatherModel(
            coord = CoordModel.fromV1(response.coord),
            weather = WeatherModel.fromV1(response.weather.firstOrNull()),
            main = MainModel.fromV1(response.main),
        )

        @VisibleForTesting
        val defaultInstance = CurrentWeatherModel(
            coord = CoordModel.defaultInstance,
            weather = null,
            main = MainModel.defaultInstance,
        )
    }
}
