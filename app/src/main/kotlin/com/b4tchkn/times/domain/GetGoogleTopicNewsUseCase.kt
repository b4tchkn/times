package com.b4tchkn.times.domain

import com.b4tchkn.times.data.GoogleNewsService
import com.b4tchkn.times.data.GoogleNewsServiceTopicType
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetGoogleTopicNewsUseCase @Inject constructor(
    private val googleNewsService: GoogleNewsService,
) {
    suspend operator fun invoke(topicType: GoogleNewsServiceTopicType) = flow {
        val response = googleNewsService.getTopicNews(topicType.name)
        emit(Result.success(response))
    }
        .catch { emit(Result.failure(it)) }
        .flowOn(Dispatchers.IO)
}
