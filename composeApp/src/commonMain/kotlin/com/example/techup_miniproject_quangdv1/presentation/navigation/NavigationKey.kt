package com.example.techup_miniproject_quangdv1.presentation.navigation

import androidx.navigation3.runtime.NavKey
import com.example.techup_miniproject_quangdv1.core.utils.ImageMode
import com.example.techup_miniproject_quangdv1.core.utils.PlatformImage
import kotlinx.serialization.Serializable

@Serializable
data object InputImageScreenKey : NavKey

@Serializable
data class SelectImageScreenKey(val mode: ImageMode) : NavKey


@Serializable
data class ImageResultScreenKey(val result: String) : NavKey