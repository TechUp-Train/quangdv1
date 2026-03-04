package com.example.composetraining.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val Number.w: Dp
    @Composable
    get() {
        val screenWidth = LocalConfiguration.current.screenWidthDp
        return (this.toFloat() * screenWidth / 100).dp
    }

val Number.h: Dp
    @Composable
    get() {
        val screenHeight = LocalConfiguration.current.screenHeightDp
        return (this.toFloat() * screenHeight / 100).dp
    }

val Number.sdp: Dp
    @Composable
    get() {
        val configuration = LocalConfiguration.current
        val minDimension = minOf(configuration.screenWidthDp, configuration.screenHeightDp)
        return (this.toFloat() * minDimension / 100).dp
    }
