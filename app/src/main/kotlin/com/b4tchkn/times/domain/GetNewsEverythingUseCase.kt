package com.b4tchkn.times.domain

import com.b4tchkn.times.data.NewsApiService
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetNewsEverythingUseCase @Inject constructor(
    private val newsApiService: NewsApiService,
) {
    suspend operator fun invoke() = flow {
        val response = newsApiService.getEverything()
        emit(Result.success(response))
    }
        .catch { emit(Result.failure(it)) }
        .flowOn(Dispatchers.IO)
}
