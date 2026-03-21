package com.example.techup_miniproject_quangdv1.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object InputImageScreenKey : NavKey

@Serializable
data object SelectImageScreenKey : NavKey


@Serializable
data class ImageResultScreenKey(val result: String) : NavKey