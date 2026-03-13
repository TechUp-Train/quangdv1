package com.example.kmptraining

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.example.kmptraining.kmp_session2.presentation.home.HomeScreen
import com.example.kmptraining.kmp_session2.presentation.home.HomeViewModel
import com.example.kmptraining.kmp_session2.presentation.navigation.HomeNavigationKey
import com.example.kmptraining.kmp_session2.presentation.navigation.SettingNavigationKey
import com.example.kmptraining.kmp_session2.presentation.newsDetail.NewsDetailScreen
import com.example.kmptraining.kmp_session2.presentation.setting.languageSetting.LanguageSettingScreen
import com.example.kmptraining.kmp_session2.presentation.setting.SettingScreen

import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navConfig = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class){
                    subclass(HomeNavigationKey.HomeKey::class, HomeNavigationKey.HomeKey.serializer())
                    subclass(HomeNavigationKey.NewsDetailKey::class, HomeNavigationKey.NewsDetailKey.serializer())

                    subclass(SettingNavigationKey.SettingKey::class, SettingNavigationKey.SettingKey.serializer())
                    subclass(SettingNavigationKey.LanguageSettingKey::class, SettingNavigationKey.LanguageSettingKey.serializer())
                }
            }
        }
        val backStack = rememberNavBackStack(navConfig, HomeNavigationKey.HomeKey)

        Scaffold {
            NavDisplay(
                backStack = backStack,
                onBack = { if (backStack.size > 1) backStack.removeLastOrNull() },
                entryProvider = entryProvider {
                    entry<HomeNavigationKey.HomeKey> {
                        val homeViewModel = koinViewModel<HomeViewModel>()
                        HomeScreen(homeViewModel)
                    }
                    entry<HomeNavigationKey.NewsDetailKey> {
                        NewsDetailScreen(it.newsId)
                    }
                    entry<SettingNavigationKey.SettingKey> {
                        SettingScreen()
                    }
                    entry<SettingNavigationKey.LanguageSettingKey> {
                        LanguageSettingScreen()
                    }
                }
            )
        }
    }
}