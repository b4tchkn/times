package com.b4tchkn.times.domain

import com.b4tchkn.times.data.GoogleNewsService
import com.b4tchkn.times.data.GoogleNewsServiceTopicType
import com.b4tchkn.times.data.MockGoogleNewsService
import com.b4tchkn.times.model.GoogleNewsRssModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior

class GetGoogleTopicNewsUseCaseTest {
    lateinit var mockGoogleNewsService: MockGoogleNewsService

    @Before
    fun setup() {
        val retrofit = Retrofit.Builder().baseUrl("https://example.com").build()
        val behavior = NetworkBehavior.create()
        val mockRetrofit = MockRetrofit.Builder(retrofit).networkBehavior(behavior).build()
        val delegate = mockRetrofit.create(GoogleNewsService::class.java)
        mockGoogleNewsService = MockGoogleNewsService(delegate)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun useCase_Success() = runTest {
        val res = mockGoogleNewsService.getTopicNews(GoogleNewsServiceTopicType.BUSINESS.name)

        Assert.assertEquals(
            res,
            GoogleNewsRssModel.defaultInstance
        )
    }
}
