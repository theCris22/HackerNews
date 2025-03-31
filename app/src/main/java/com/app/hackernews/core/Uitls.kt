package com.app.hackernews.core

import android.content.Intent
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.browser.customtabs.CustomTabsIntent

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
