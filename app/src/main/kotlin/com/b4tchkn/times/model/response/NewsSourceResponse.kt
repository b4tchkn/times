package com.b4tchkn.times.model.response

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class NewsSourceResponse(
    @JsonNames("name")
    val name: String?,
)
