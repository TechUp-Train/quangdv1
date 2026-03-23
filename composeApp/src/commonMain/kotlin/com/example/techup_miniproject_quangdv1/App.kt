package com.example.techup_miniproject_quangdv1

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.example.techup_miniproject_quangdv1.core.theme.AppTheme
import com.example.techup_miniproject_quangdv1.presentation.navigation.ImageResultScreenKey
import com.example.techup_miniproject_quangdv1.presentation.navigation.InputImageScreenKey
import com.example.techup_miniproject_quangdv1.presentation.navigation.SelectImageScreenKey
import com.example.techup_miniproject_quangdv1.presentation.screens.imageInput.ImageInputScreen
import com.example.techup_miniproject_quangdv1.presentation.screens.imageSelectScreen.GalleryScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
@Preview
fun App() {
    AppTheme {
        val navConfig = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(InputImageScreenKey::class, InputImageScreenKey.serializer())
                    subclass(SelectImageScreenKey::class, SelectImageScreenKey.serializer())
                    subclass(ImageResultScreenKey::class, ImageResultScreenKey.serializer())
                }
            }
        }

        val backStack = rememberNavBackStack(navConfig, InputImageScreenKey)

        NavDisplay(
            backStack = backStack,
            onBack = { if (backStack.size > 1) backStack.removeLastOrNull() },
            entryProvider = entryProvider {
                entry<InputImageScreenKey> {
                    ImageInputScreen(
                        onSelectImages = { mode ->
                            backStack.add(SelectImageScreenKey(mode))
                        }
                    )
                }
                entry<SelectImageScreenKey> {
                    GalleryScreen(
                        imageMode = it.mode,
                        onSelectImages = {}
                    )
                }
                entry<ImageResultScreenKey> {

                }
            }
        )
    }
}