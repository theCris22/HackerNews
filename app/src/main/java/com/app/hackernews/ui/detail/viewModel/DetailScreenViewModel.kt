package com.app.hackernews.ui.detail.viewModel

import android.content.Intent
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel
    @Inject
    constructor() : ViewModel() {
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
    }
