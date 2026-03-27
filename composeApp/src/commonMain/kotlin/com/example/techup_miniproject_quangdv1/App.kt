package com.example.techup_miniproject_quangdv1

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.example.techup_miniproject_quangdv1.core.theme.AppTheme
import com.example.techup_miniproject_quangdv1.core.utils.PlatformImage
import com.example.techup_miniproject_quangdv1.presentation.navigation.ImageResultScreenKey
import com.example.techup_miniproject_quangdv1.presentation.navigation.InputImageScreenKey
import com.example.techup_miniproject_quangdv1.presentation.navigation.LocalResultEventBus
import com.example.techup_miniproject_quangdv1.presentation.navigation.ResultEffect
import com.example.techup_miniproject_quangdv1.presentation.navigation.ResultEventBus
import com.example.techup_miniproject_quangdv1.presentation.navigation.SelectImageScreenKey
import com.example.techup_miniproject_quangdv1.presentation.screens.generatedImage.GeneratedImageScreen
import com.example.techup_miniproject_quangdv1.presentation.screens.imageGallery.GalleryScreen
import com.example.techup_miniproject_quangdv1.presentation.screens.imageInput.ImageInputScreen
import com.example.techup_miniproject_quangdv1.presentation.screens.imageInput.ImageInputViewModel
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    AppTheme {
        val resultBus = remember { ResultEventBus() }
        CompositionLocalProvider(LocalResultEventBus provides resultBus) {
            val navConfig =
                SavedStateConfiguration {
                    serializersModule =
                        SerializersModule {
                            polymorphic(NavKey::class) {
                                subclass(InputImageScreenKey::class, InputImageScreenKey.serializer())
                                subclass(SelectImageScreenKey::class, SelectImageScreenKey.serializer())
                                subclass(ImageResultScreenKey::class, ImageResultScreenKey.serializer())
                            }
                        }
                }

            val backStack = rememberNavBackStack(navConfig, InputImageScreenKey)

            val onBack: () -> Unit = {
                if (backStack.size > 1) {
                    backStack.removeLastOrNull()
                }
            }

            NavDisplay(
                backStack = backStack,
                onBack = onBack,
                entryProvider =
                    entryProvider {
                        entry<InputImageScreenKey> {
                            val viewModel: ImageInputViewModel = koinViewModel()

                            ResultEffect<List<PlatformImage>>(resultKey = "SelectedImages") { images ->
                                viewModel.setSelectedImages(images)
                            }

                            ImageInputScreen(
                                onSelectImages = { mode ->
                                    backStack.add(SelectImageScreenKey(mode))
                                },
                                onGeneratedResult = { url ->
                                    backStack.add(ImageResultScreenKey(url))
                                },
                                viewModel = viewModel,
                            )
                        }
                        entry<SelectImageScreenKey> { key ->
                            val resultBus = LocalResultEventBus.current

                            GalleryScreen(
                                imageMode = key.mode,
                                onSelectImages = { images ->
                                    resultBus.sendResult<List<PlatformImage>>(
                                        resultKey = "SelectedImages",
                                        result = images,
                                    )
                                    backStack.removeLastOrNull()
                                },
                                onBack = onBack,
                            )
                        }
                        entry<ImageResultScreenKey> { key ->
                            GeneratedImageScreen(
                                url = key.result,
                                onBack = onBack,
                            )
                        }
                    },
            )
        }
    }
}
