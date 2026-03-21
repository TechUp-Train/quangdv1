package com.example.kmptraining

import androidx.compose.ui.window.ComposeUIViewController
import com.example.kmptraining.kmp_session4.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) { App() }