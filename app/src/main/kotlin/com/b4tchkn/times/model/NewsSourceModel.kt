package com.b4tchkn.times.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class NewsSourceModel(
    @JsonNames("name")
    val name: String?,
)
