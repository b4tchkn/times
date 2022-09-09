package com.b4tchkn.times.model

import androidx.annotation.VisibleForTesting
import com.b4tchkn.times.model.response.NewsSourceResponse

data class NewsSourceModel(
    val name: String?,
) {
    companion object {
        fun fromV1(response: NewsSourceResponse?) = NewsSourceModel(
            name = response?.name,
        )

        @VisibleForTesting
        val defaultInstance = NewsSourceModel(
            name = null,
        )
    }
}
