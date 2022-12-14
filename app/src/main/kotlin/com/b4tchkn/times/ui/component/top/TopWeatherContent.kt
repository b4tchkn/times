package com.b4tchkn.times.ui.component.top

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.b4tchkn.times.R
import com.b4tchkn.times.model.CurrentWeatherModel
import com.b4tchkn.times.ui.theme.AppColor
import com.b4tchkn.times.util.formatDayOfWeekJp
import java.time.LocalDateTime
import kotlin.math.ceil

@Composable
fun TopWeatherContent(
    currentWeather: CurrentWeatherModel,
    onClicked: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(AppColor.DarkGrey)
            .clickable { onClicked() }
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val now = LocalDateTime.now()
            val context = LocalContext.current
            Text(
                text = stringResource(
                    R.string.month_day_dayOfWeek,
                    now.month.value,
                    now.dayOfMonth,
                    formatDayOfWeekJp(context, now.dayOfWeek.value),
                ),
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.weight(1F))
            Weather(currentWeather = currentWeather)
        }
    }
}

@Composable
private fun Weather(currentWeather: CurrentWeatherModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val iconUrl =
            "http://openweathermap.org/img/wn/${currentWeather.weather?.icon}@2x.png"
        AsyncImage(
            modifier = Modifier.size(width = 54.dp, height = 54.dp),
            model = iconUrl,
            contentDescription = currentWeather.weather?.description
        )
        val temp = ceil((currentWeather.main.temp * 10.0) / 10.0)
        Text(
            text = "$temp ${stringResource(id = R.string.unit_temp)}",
            fontSize = 18.sp,
        )
    }
}
