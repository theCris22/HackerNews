package com.app.hackernews.data.network.response

import com.app.hackernews.data.network.models.Hit

data class AndroidNewsResponse(
    private val hits: List<Hit>,
)
