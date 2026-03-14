package com.example.kmptraining

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.ktor3.KtorNetworkFetcherFactory
import com.example.kmptraining.kmp_session2.presentation.components.BaseBottomNavigationBar
import com.example.kmptraining.kmp_session2.presentation.components.BaseTopAppBar
import com.example.kmptraining.kmp_session2.presentation.home.HomeScreen
import com.example.kmptraining.kmp_session2.presentation.home.HomeViewModel
import com.example.kmptraining.kmp_session2.presentation.navigation.HomeNavigationKey
import com.example.kmptraining.kmp_session2.presentation.navigation.SettingNavigationKey
import com.example.kmptraining.kmp_session2.presentation.newsDetail.NewsDetailScreen
import com.example.kmptraining.kmp_session2.presentation.setting.SettingScreen
import com.example.kmptraining.kmp_session2.presentation.setting.languageSetting.LanguageSettingScreen
import com.example.kmptraining.kmp_session2.ui.NewsAppTheme
import kmptraining.composeapp.generated.resources.Res
import kmptraining.composeapp.generated.resources.ic_search
import kmptraining.composeapp.generated.resources.search
import kmptraining.composeapp.generated.resources.session2_app_name
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Session2App(modifier: Modifier = Modifier) {
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
            .components {
                add(KtorNetworkFetcherFactory())
            }
            .build()
    }
    NewsAppTheme {
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
                    BaseBottomNavigationBar(backStack = backStack, currentScreen = currentScreen)
                }
            },
            topBar = {
                if (currentScreen is HomeNavigationKey.HomeKey) {
                    BaseTopAppBar(
                        title = Res.string.session2_app_name,
                        actions = {
                            IconButton(onClick = {}) {
                                Icon(
                                    painter = painterResource(Res.drawable.ic_search),
                                    contentDescription = stringResource(Res.string.search),
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
                        HomeScreen(onNewsClick = { news ->
                            backStack.add(HomeNavigationKey.NewsDetailKey(news.id))
                        }, viewModel = homeViewModel)
                    }
                    entry<HomeNavigationKey.NewsDetailKey> {
                        NewsDetailScreen(
                            it.newsId,
                            onBack = { backStack.removeLastOrNull() },
                        )
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