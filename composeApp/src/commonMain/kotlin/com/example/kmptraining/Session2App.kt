package com.example.kmptraining

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import kmptraining.composeapp.generated.resources.Res
import kmptraining.composeapp.generated.resources.ic_home
import kmptraining.composeapp.generated.resources.ic_search
import kmptraining.composeapp.generated.resources.ic_setting

import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun Session2App() {
    MaterialTheme {
        val navConfig = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(
                        HomeNavigationKey.HomeKey::class,
                        HomeNavigationKey.HomeKey.serializer()
                    )
                    subclass(
                        HomeNavigationKey.NewsDetailKey::class,
                        HomeNavigationKey.NewsDetailKey.serializer()
                    )

                    subclass(
                        SettingNavigationKey.SettingKey::class,
                        SettingNavigationKey.SettingKey.serializer()
                    )
                    subclass(
                        SettingNavigationKey.LanguageSettingKey::class,
                        SettingNavigationKey.LanguageSettingKey.serializer()
                    )
                }
            }
        }
        val backStack = rememberNavBackStack(navConfig, HomeNavigationKey.HomeKey)
        val currentScreen = backStack.lastOrNull()
        val showBottomBar =
            currentScreen is HomeNavigationKey.HomeKey || currentScreen is SettingNavigationKey.SettingKey

        Scaffold(
            bottomBar = {
                if (showBottomBar) {
                    NavigationBar {
                        NavigationBarItem(
                            selected = currentScreen is HomeNavigationKey.HomeKey,
                            onClick = {
                                if (currentScreen !is HomeNavigationKey.HomeKey) {
                                    backStack.clear()
                                    backStack.add(HomeNavigationKey.HomeKey)
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(Res.drawable.ic_home),
                                    contentDescription = "Home",
                                    modifier = Modifier.size(30.dp)
                                )
                            },
                            label = { Text("Home") }
                        )
                        NavigationBarItem(
                            selected = currentScreen is SettingNavigationKey.SettingKey,
                            onClick = {
                                if (currentScreen !is SettingNavigationKey.SettingKey) {
                                    backStack.clear()
                                    backStack.add(SettingNavigationKey.SettingKey)
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(Res.drawable.ic_setting),
                                    contentDescription = "Settings",
                                    modifier = Modifier.size(30.dp)
                                )
                            },
                            label = { Text("Settings") }
                        )
                    }
                }
            },
            topBar = {
                if (currentScreen is HomeNavigationKey.HomeKey) {
                    TopAppBar(
                        title = { Text("News App") },
                        actions = {
                            IconButton(onClick = {}) {
                                Icon(
                                    painter = painterResource(Res.drawable.ic_search),
                                    contentDescription = "Search",
                                    modifier = Modifier.size(25.dp)
                                )
                            }
                        }
                    )
                }
            }
        ) { innerPadding ->
            NavDisplay(
                modifier = Modifier.padding(innerPadding),
                backStack = backStack,
                onBack = { if (backStack.size > 1) backStack.removeLastOrNull() },
                entryProvider = entryProvider {
                    entry<HomeNavigationKey.HomeKey> {
                        val homeViewModel: HomeViewModel = koinInject()
                        HomeScreen(homeViewModel, onNewsClick = { news ->
                            backStack.add(HomeNavigationKey.NewsDetailKey(news.id))
                        })
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