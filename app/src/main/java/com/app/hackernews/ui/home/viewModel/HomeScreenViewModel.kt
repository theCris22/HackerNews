package com.app.hackernews.ui.home.viewModel

import android.content.Intent
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

        fun openCustomTab(
            url: String,
            launcher: ActivityResultLauncher<Intent>,
        ) {
            val intent = CustomTabsIntent.Builder().build()

            val customTabsIntent =
                intent.intent.apply {
                    data = Uri.parse(url)
                }

            launcher.launch(customTabsIntent)
        }

        init {
            getAndroidNews()
        }
    }
