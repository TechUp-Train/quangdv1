package com.example.techup_miniproject_quangdv1

import androidx.compose.ui.window.ComposeUIViewController
import com.example.techup_miniproject_quangdv1.di.initKoin

/**
 * iOS entry point.
 * Initializes Koin before creating the Compose UI controller.
 */
fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}