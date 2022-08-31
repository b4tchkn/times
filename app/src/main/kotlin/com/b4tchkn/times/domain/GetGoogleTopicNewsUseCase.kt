package com.b4tchkn.times.domain

import com.b4tchkn.times.data.GoogleNewsService
import com.b4tchkn.times.data.GoogleNewsServiceTopicType
import com.b4tchkn.times.model.GoogleNewsRssModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetGoogleTopicNewsUseCase @Inject constructor(
    private val googleNewsService: GoogleNewsService,
) {
    operator fun invoke(topicType: GoogleNewsServiceTopicType): Flow<GoogleNewsRssModel> =
        flow {
            emit(
                googleNewsService.getTopicNews(topicType.name)
            )
        }
}
