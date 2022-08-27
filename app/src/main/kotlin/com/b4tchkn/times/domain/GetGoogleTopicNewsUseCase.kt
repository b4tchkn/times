package com.b4tchkn.times.domain

import com.b4tchkn.times.data.GoogleNewsService
import com.b4tchkn.times.data.GoogleNewsServiceTopicType
import com.b4tchkn.times.model.GoogleNewsRssModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetGoogleTopicNewsUseCase @Inject constructor(
    private val googleNewsService: GoogleNewsService,
) {
    suspend operator fun invoke(topicType: GoogleNewsServiceTopicType): GoogleNewsRssModel =
        withContext(Dispatchers.IO) {
            googleNewsService.getTopicNews(topicType.name)
        }
}
