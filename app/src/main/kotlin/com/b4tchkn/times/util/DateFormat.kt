package com.b4tchkn.times.util

import android.content.Context
import com.b4tchkn.times.R
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import kotlin.math.absoluteValue

fun formatBeforeDateTimeFromNowString(context: Context, dateTime: LocalDateTime): String {
    val now = LocalDateTime.now()
    val hourDiff = ChronoUnit.HOURS.between(now, dateTime).absoluteValue
    val minDiff = ChronoUnit.MINUTES.between(now, dateTime).absoluteValue

    val hour = if (hourDiff == 0L) 0 else hourDiff.toInt()
    val min = if (minDiff == 0L) 0 else minDiff - 60 * hourDiff

    return if (hour != 0) {
        context.getString(R.string.before_hour_min, hour, min)
    } else {
        context.getString(R.string.before_min, minDiff)
    }
}
