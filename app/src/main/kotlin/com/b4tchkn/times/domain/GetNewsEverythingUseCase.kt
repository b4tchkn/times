package com.b4tchkn.times.domain

import com.b4tchkn.times.data.NewsApiService
import com.b4tchkn.times.di.IODispatcher
import com.b4tchkn.times.model.NewsModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetNewsEverythingUseCase @Inject constructor(
    private val newsApiService: NewsApiService,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(): NewsModel = withContext(ioDispatcher) {
        newsApiService.getEverything()
    }
}
