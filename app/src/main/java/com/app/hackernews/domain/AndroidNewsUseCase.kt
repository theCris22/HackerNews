package com.app.hackernews.domain

import com.app.hackernews.data.network.HackerNewsRepository
import javax.inject.Inject

class AndroidNewsUseCase
    @Inject
    constructor(
        private val repository: HackerNewsRepository,
    ) {
        suspend fun invoke() = repository.getLatestAndroidNews()
    }
