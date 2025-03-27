package com.app.hackernews.data.network.models

sealed class RequestState<out T> {
    data class Success<out T>(
        val data: T,
    ) : RequestState<T>()

    data class Error<out T>(
        val message: String,
        val data: T? = null,
    ) : RequestState<T>()

    data object Idle : RequestState<Nothing>()

    data object Loading : RequestState<Nothing>()
}
