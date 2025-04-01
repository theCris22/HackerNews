package com.app.hackernews.data.network

import com.app.hackernews.data.network.response.AndroidNewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HackerNewsApi {
    @GET(SEARCH_BY_DATE)
    suspend fun getLatestAndroidNews(
        @Query(QUERY) query: String = ANDROID_QUERY,
    ): Response<AndroidNewsResponse>
}
