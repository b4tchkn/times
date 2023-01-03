package com.b4tchkn.times.model

import android.content.Context
import com.b4tchkn.times.R

enum class GoogleNewsServiceTopicTypeModel {
    WORLD,
    NATION,
    BUSINESS,
    TECHNOLOGY,
    ENTERTAINMENT,
    SPORTS,
    SCIENCE;

    companion object {
        fun label(type: GoogleNewsServiceTopicTypeModel, context: Context) = when (type) {
            WORLD -> context.getString(R.string.category_topic_world)
            NATION -> context.getString(R.string.category_topic_nation)
            BUSINESS -> context.getString(R.string.category_topic_business)
            TECHNOLOGY -> context.getString(R.string.category_topic_technology)
            ENTERTAINMENT -> context.getString(R.string.category_topic_entertainment)
            SPORTS -> context.getString(R.string.category_topic_sport)
            SCIENCE -> context.getString(R.string.category_topic_science)
        }
    }
}
