package com.b4tchkn.times.domain

import com.b4tchkn.times.data.NewsApiService
import com.b4tchkn.times.model.NewsModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetNewsTopHeadlinesUseCase @Inject constructor(
    private val newsApiService: NewsApiService,
) {
    suspend operator fun invoke(): Flow<NewsModel> = flow {
        emit(
            newsApiService.getTopHeadlines()
        )
    }
}
