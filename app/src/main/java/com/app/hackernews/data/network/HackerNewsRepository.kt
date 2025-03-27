package com.app.hackernews.data.network

import com.app.hackernews.data.network.models.RequestState
import com.app.hackernews.data.network.response.AndroidNewsResponse
import javax.inject.Inject

class HackerNewsRepository
    @Inject
    constructor(
        private val hackerNewsApi: HackerNewsApi,
    ) {
        suspend fun getLatestAndroidNews(): RequestState<AndroidNewsResponse> =
            try {
                val response = hackerNewsApi.getLatestAndroidNews()

                if (response.isSuccessful && response.body() != null) {
                    RequestState.Success(response.body()!!)
                } else {
                    RequestState.Error(response.message())
                }
            } catch (ex: Exception) {
                RequestState.Error(ex.message.toString())
            }
    }
