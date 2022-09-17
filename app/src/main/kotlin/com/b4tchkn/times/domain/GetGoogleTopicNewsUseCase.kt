package com.b4tchkn.times.domain

import com.b4tchkn.times.data.GoogleNewsService
import com.b4tchkn.times.di.IODispatcher
import com.b4tchkn.times.model.GoogleNewsRssModel
import com.b4tchkn.times.model.GoogleNewsServiceTopicTypeModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetGoogleTopicNewsUseCase @Inject constructor(
    private val googleNewsService: GoogleNewsService,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(topicType: GoogleNewsServiceTopicTypeModel) = flow {
        val response = googleNewsService.getTopicNews(topicType.name)
        val model = GoogleNewsRssModel.fromV1(response)
        emit(Result.success(model))
    }
        .catch { emit(Result.failure(it)) }
        .flowOn(ioDispatcher)
}
