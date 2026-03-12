package com.example.kmptraining.migrate.common

import androidx.compose.runtime.Composable

data class SubSession(
    val name: String,
    val content: @Composable () -> Unit,
)