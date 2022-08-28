package com.b4tchkn.times.domain

import com.b4tchkn.times.data.NewsApiService
import com.b4tchkn.times.model.NewsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetNewsTopHeadlinesUseCase @Inject constructor(
    private val newsApiService: NewsApiService,
) {
    suspend operator fun invoke(): NewsModel = withContext(Dispatchers.IO) {
        newsApiService.getTopHeadlines()
    }
}