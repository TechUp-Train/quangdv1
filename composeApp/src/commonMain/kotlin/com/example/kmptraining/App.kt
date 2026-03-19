package com.example.kmptraining

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.example.kmptraining.kmp_session5.navigation.GalleryKey
import com.example.kmptraining.kmp_session5.navigation.SelectedImagesHolder
import com.example.kmptraining.kmp_session5.navigation.SelectedImagesKey
import com.example.kmptraining.kmp_session5.screens.GalleryScreen
import com.example.kmptraining.kmp_session5.screens.SelectedImagesScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun App() {
    val navConfig = SavedStateConfiguration {
        serializersModule = SerializersModule {
            polymorphic(NavKey::class) {
                subclass(GalleryKey::class, GalleryKey.serializer())
                subclass(SelectedImagesKey::class, SelectedImagesKey.serializer())
            }
        }
    }
    val backStack = rememberNavBackStack(navConfig, GalleryKey)
    MaterialTheme {
        NavDisplay(
            backStack = backStack,
            onBack = { if (backStack.size > 1) backStack.removeLastOrNull() },
            entryProvider = entryProvider {
                entry<GalleryKey> {
                    GalleryScreen { images ->
                        SelectedImagesHolder.selectedImages = images
                        backStack.add(SelectedImagesKey)
                    }
                }
                entry<SelectedImagesKey> {
                    SelectedImagesScreen(
                        initialImages = SelectedImagesHolder.selectedImages,
                        onBack = { backStack.removeLastOrNull() }
                    )
                }
            }
        )
    }
}