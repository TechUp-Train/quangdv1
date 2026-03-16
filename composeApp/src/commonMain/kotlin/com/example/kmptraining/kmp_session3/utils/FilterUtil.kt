package com.example.kmptraining.kmp_session3.utils

import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix

enum class EditorFilter {
    NONE,
    GRAYSCALE,
    SEPIA,
    INVERT,
    VINTAGE,
    COOL,
    WARM,
    HIGH_CONTRAST,
    FADE,
    NIGHT
}

fun EditorFilter.toColorFilter(): ColorFilter? {

    return when (this) {

        EditorFilter.NONE -> null

        EditorFilter.GRAYSCALE -> {
            val matrix = ColorMatrix()
            matrix.setToSaturation(0f)
            ColorFilter.colorMatrix(matrix)
        }

        EditorFilter.SEPIA -> {
            ColorFilter.colorMatrix(
                ColorMatrix(
                    floatArrayOf(
                        0.393f, 0.769f, 0.189f, 0f, 0f,
                        0.349f, 0.686f, 0.168f, 0f, 0f,
                        0.272f, 0.534f, 0.131f, 0f, 0f,
                        0f, 0f, 0f, 1f, 0f
                    )
                )
            )
        }

        EditorFilter.INVERT -> {
            ColorFilter.colorMatrix(
                ColorMatrix(
                    floatArrayOf(
                        -1f, 0f, 0f, 0f, 255f,
                        0f, -1f, 0f, 0f, 255f,
                        0f, 0f, -1f, 0f, 255f,
                        0f, 0f, 0f, 1f, 0f
                    )
                )
            )
        }

        EditorFilter.VINTAGE -> {
            ColorFilter.colorMatrix(
                ColorMatrix(
                    floatArrayOf(
                        0.9f, 0.5f, 0.1f, 0f, 0f,
                        0.3f, 0.8f, 0.1f, 0f, 0f,
                        0.2f, 0.3f, 0.5f, 0f, 0f,
                        0f, 0f, 0f, 1f, 0f
                    )
                )
            )
        }

        EditorFilter.COOL -> {
            ColorFilter.colorMatrix(
                ColorMatrix(
                    floatArrayOf(
                        0.9f, 0f, 0f, 0f, 0f,
                        0f, 0.9f, 0f, 0f, 0f,
                        0f, 0f, 1.2f, 0f, 0f,
                        0f, 0f, 0f, 1f, 0f
                    )
                )
            )
        }

        EditorFilter.WARM -> {
            ColorFilter.colorMatrix(
                ColorMatrix(
                    floatArrayOf(
                        1.2f, 0f, 0f, 0f, 0f,
                        0f, 1.1f, 0f, 0f, 0f,
                        0f, 0f, 0.9f, 0f, 0f,
                        0f, 0f, 0f, 1f, 0f
                    )
                )
            )
        }

        EditorFilter.HIGH_CONTRAST -> {
            ColorFilter.colorMatrix(
                ColorMatrix(
                    floatArrayOf(
                        1.5f, 0f, 0f, 0f, -128f,
                        0f, 1.5f, 0f, 0f, -128f,
                        0f, 0f, 1.5f, 0f, -128f,
                        0f, 0f, 0f, 1f, 0f
                    )
                )
            )
        }

        EditorFilter.FADE -> {
            ColorFilter.colorMatrix(
                ColorMatrix(
                    floatArrayOf(
                        0.8f, 0f, 0f, 0f, 30f,
                        0f, 0.8f, 0f, 0f, 30f,
                        0f, 0f, 0.8f, 0f, 30f,
                        0f, 0f, 0f, 1f, 0f
                    )
                )
            )
        }

        EditorFilter.NIGHT -> {
            ColorFilter.colorMatrix(
                ColorMatrix(
                    floatArrayOf(
                        0.6f, 0f, 0f, 0f, 0f,
                        0f, 0.8f, 0f, 0f, 0f,
                        0f, 0f, 1.4f, 0f, 0f,
                        0f, 0f, 0f, 1f, 0f
                    )
                )
            )
        }
    }
}