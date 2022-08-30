package com.b4tchkn.times.data

import com.b4tchkn.times.model.GoogleNewsRssModel
import retrofit2.mock.BehaviorDelegate

class MockGoogleNewsService(
    private val delegate: BehaviorDelegate<GoogleNewsService>,
) : GoogleNewsService {
    override suspend fun getTopicNews(topic: String): GoogleNewsRssModel {
        val response = GoogleNewsRssModel.defaultInstance
        return delegate.returningResponse(response)
            .getTopicNews(topic)
    }
}
