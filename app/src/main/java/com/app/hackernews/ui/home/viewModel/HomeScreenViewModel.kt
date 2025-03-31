package com.app.hackernews.ui.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.hackernews.data.network.models.Hit
import com.app.hackernews.data.network.models.RequestState
import com.app.hackernews.data.network.response.AndroidNewsResponse
import com.app.hackernews.domain.AndroidNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel
    @Inject
    constructor(
        private val androidNewsUseCase: AndroidNewsUseCase,
    ) : ViewModel() {
        private val _androidNewsState = MutableStateFlow<RequestState<AndroidNewsResponse>>(RequestState.Loading)
        val androidNewsState get() = _androidNewsState

        fun getAndroidNews() =
            viewModelScope.launch {
                _androidNewsState.value = RequestState.Loading
                _androidNewsState.value = androidNewsUseCase.invoke()
            }

        fun removeItem(hit: Hit) {
            val currentState = _androidNewsState.value
            if (currentState is RequestState.Success) {
                val currentList = currentState.data.hits.toMutableList()
                currentList.remove(hit)
                _androidNewsState.value =
                    RequestState.Success(
                        currentState.data.copy(hits = currentList),
                    )
            }
        }

        init {
            getAndroidNews()
        }
    }
