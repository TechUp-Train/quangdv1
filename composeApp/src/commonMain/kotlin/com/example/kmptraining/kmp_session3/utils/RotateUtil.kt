package com.example.kmptraining.kmp_session3.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

fun ImageBitmap.rotate(degrees: Float): ImageBitmap {

    val srcBitmap = this

    val newWidth = if (degrees % 180f == 0f) width else height
    val newHeight = if (degrees % 180f == 0f) height else width

    val rotatedBitmap = ImageBitmap(newWidth, newHeight)
    val canvas = Canvas(rotatedBitmap)

    CanvasDrawScope().draw(
        density = Density(1f),
        layoutDirection = LayoutDirection.Ltr,
        canvas = canvas,
        size = Size(newWidth.toFloat(), newHeight.toFloat())
    ) {
        rotate(
            degrees = degrees,
            pivot = center
        ) {
            drawImage(
                image = srcBitmap,
                topLeft = Offset(
                    (newWidth - srcBitmap.width) / 2f,
                    (newHeight - srcBitmap.height) / 2f
                )
            )
        }
    }

    return rotatedBitmap
}