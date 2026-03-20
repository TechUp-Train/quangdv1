package com.example.kmptraining

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.example.kmptraining.kmp_session4.core.theme.GitHubClientTheme
import com.example.kmptraining.kmp_session4.presentation.navigation.AppNavigationBar
import com.example.kmptraining.kmp_session4.presentation.navigation.AppNavigationKey
import com.example.kmptraining.kmp_session4.presentation.screens.explore.ExploreScreen
import com.example.kmptraining.kmp_session4.presentation.screens.home.HomeScreen
import com.example.kmptraining.kmp_session4.presentation.screens.profile.ProfileScreen
import com.example.kmptraining.kmp_session4.presentation.screens.repoDetail.RepoDetailScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
@Preview
fun App() {
    GitHubClientTheme {
        val navConfig = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(AppNavigationKey.Home::class, AppNavigationKey.Home.serializer())
                    subclass(AppNavigationKey.Explore::class, AppNavigationKey.Explore.serializer())
                    subclass(AppNavigationKey.Profile::class, AppNavigationKey.Profile.serializer())
                    subclass(
                        AppNavigationKey.RepoDetail::class,
                        AppNavigationKey.RepoDetail.serializer()
                    )
                }
            }
        }
        val backStack = rememberNavBackStack(navConfig, AppNavigationKey.Home)

        Scaffold(
            bottomBar = {
                AppNavigationBar(
                    currentKey = backStack.lastOrNull(),
                    onNavigate = { key ->
                        backStack.add(key)
                    }
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                NavDisplay(
                    backStack = backStack,
                    onBack = { if (backStack.size > 1) backStack.removeLastOrNull() },
                    entryProvider = entryProvider {
                        entry<AppNavigationKey.Home> {
                            HomeScreen(
                                onSearchRepos = { backStack.add(AppNavigationKey.Explore) },
                                onRepoClick = { repo ->
                                    backStack.add(AppNavigationKey.RepoDetail(repo.owner.login, repo.name))
                                }
                            )
                        }
    
                        entry<AppNavigationKey.Explore> {
                            ExploreScreen(
                                onRepoClick = { repo ->
                                    backStack.add(AppNavigationKey.RepoDetail(repo.owner.login, repo.name))
                                },
                            )
                        }
                        entry<AppNavigationKey.Profile> {
                            ProfileScreen(
                                onRepoClick = { repo ->
                                    backStack.add(AppNavigationKey.RepoDetail(repo.owner.login, repo.name))
                                }
                            )
                        }
    
                        entry<AppNavigationKey.RepoDetail> {
                            RepoDetailScreen(
                                owner = it.owner,
                                repo = it.repo
                            )
                        }
                    }
                )
            }
        }
    }
}