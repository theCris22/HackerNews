package com.app.hackernews.domain

import com.app.hackernews.data.network.HackerNewsRepository
import javax.inject.Inject

class GetNewsFromDatabaseUseCase
    @Inject
    constructor(
        private val repository: HackerNewsRepository,
    ) {
        suspend fun invoke() = repository.returnDataFromDatabase()
    }
