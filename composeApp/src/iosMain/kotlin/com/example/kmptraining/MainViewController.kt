package com.example.kmptraining

import androidx.compose.ui.window.ComposeUIViewController
import com.example.kmptraining.kmp_session2.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { Session2App() }