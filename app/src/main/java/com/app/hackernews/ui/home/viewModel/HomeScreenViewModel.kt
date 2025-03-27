package com.app.hackernews.ui.home.viewModel

import androidx.lifecycle.ViewModel
import com.app.hackernews.domain.AndroidNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel
    @Inject
    constructor(
        private val androidNewsUseCase: AndroidNewsUseCase,
    ) : ViewModel()
