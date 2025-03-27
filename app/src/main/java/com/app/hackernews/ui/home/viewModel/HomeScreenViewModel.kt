package com.app.hackernews.ui.home.viewModel

import androidx.lifecycle.ViewModel
import com.app.hackernews.domain.AndroidNewsUseCase
import javax.inject.Inject

class HomeScreenViewModel
    @Inject
    constructor(
        private val androidNewsUseCase: AndroidNewsUseCase,
    ) : ViewModel()
