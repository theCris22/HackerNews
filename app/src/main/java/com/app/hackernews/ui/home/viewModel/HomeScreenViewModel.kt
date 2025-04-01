package com.app.hackernews.ui.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.hackernews.data.network.models.Hit
import com.app.hackernews.data.network.models.RequestState
import com.app.hackernews.domain.AndroidNewsUseCase
import com.app.hackernews.domain.DeleteNewsByIdUseCase
import com.app.hackernews.domain.GetNewsFromDatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel
    @Inject
    constructor(
        private val androidNewsUseCase: AndroidNewsUseCase,
        private val getNewsFromDatabaseUseCase: GetNewsFromDatabaseUseCase,
        private val deleteNewsByIdUseCase: DeleteNewsByIdUseCase,
    ) : ViewModel() {
        private val _androidNewsState = MutableStateFlow<RequestState<List<Hit>>>(RequestState.Loading)
        val androidNewsState get() = _androidNewsState

        fun getAndroidNews() =
            viewModelScope.launch {
                _androidNewsState.value = RequestState.Loading
                _androidNewsState.value = androidNewsUseCase.invoke()
            }

        fun getDataFromDatabase() {
            viewModelScope.launch {
                _androidNewsState.value = RequestState.Success(getNewsFromDatabaseUseCase.invoke())
            }
        }

        fun deleteNews(objectId: String) =
            viewModelScope.launch {
                deleteNewsByIdUseCase.invoke(objectId)
            }

        init {
            getAndroidNews()
        }
    }
