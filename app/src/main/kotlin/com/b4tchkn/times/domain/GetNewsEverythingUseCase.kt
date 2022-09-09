package com.b4tchkn.times.domain

import com.b4tchkn.times.data.NewsApiService
import com.b4tchkn.times.di.IODispatcher
import com.b4tchkn.times.model.NewsModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetNewsEverythingUseCase @Inject constructor(
    private val newsApiService: NewsApiService,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke() = flow {
        val response = newsApiService.getEverything()
        val model = NewsModel.fromV1(response)
        emit(Result.success(model))
    }
        .catch { emit(Result.failure(it)) }
        .flowOn(ioDispatcher)
}
