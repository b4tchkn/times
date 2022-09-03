package com.b4tchkn.times.domain

import com.b4tchkn.times.data.GoogleNewsService
import com.b4tchkn.times.data.GoogleNewsServiceTopicType
import com.b4tchkn.times.model.GoogleNewsRssModel
import javax.inject.Inject

class GetGoogleTopicNewsUseCase @Inject constructor(
    private val googleNewsService: GoogleNewsService,
) {
    suspend operator fun invoke(topicType: GoogleNewsServiceTopicType): Result<GoogleNewsRssModel> =
        kotlin.runCatching {
            googleNewsService.getTopicNews(topicType.name)
        }
}
