package com.example.techup_miniproject_quangdv1

import androidx.compose.ui.window.ComposeUIViewController
import com.example.techup_miniproject_quangdv1.core.di.iosModule
import com.example.techup_miniproject_quangdv1.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin(extraModules = arrayOf(iosModule))
    }
) {
    App()
}