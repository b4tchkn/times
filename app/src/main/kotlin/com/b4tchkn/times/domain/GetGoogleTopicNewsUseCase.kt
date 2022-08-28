package com.b4tchkn.times.domain

import com.b4tchkn.times.data.GoogleNewsService
import com.b4tchkn.times.data.GoogleNewsServiceTopicType
import com.b4tchkn.times.di.IODispatcher
import com.b4tchkn.times.model.GoogleNewsRssModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetGoogleTopicNewsUseCase @Inject constructor(
    private val googleNewsService: GoogleNewsService,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(topicType: GoogleNewsServiceTopicType): GoogleNewsRssModel =
        withContext(ioDispatcher) {
            googleNewsService.getTopicNews(topicType.name)
        }
}
