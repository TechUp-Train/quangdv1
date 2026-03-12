package com.example.kmptraining.migrate.session5.session5_4.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.example.kmptraining.migrate.session5.session5_4.data.UserData
import com.example.kmptraining.migrate.session5.session5_4.data.registeredUsers
import com.example.kmptraining.migrate.session5.session5_4.route.MainKey
import com.example.kmptraining.migrate.session5.session5_4.route.Tab
import com.example.kmptraining.migrate.session5.session5_4.screens.main.discover.DiscoverScreen
import com.example.kmptraining.migrate.session5.session5_4.screens.main.feed.FeedScreen
import com.example.kmptraining.migrate.session5.session5_4.screens.main.feed.PostDetailScreen
import com.example.kmptraining.migrate.session5.session5_4.screens.main.profile.EditProfileScreen
import com.example.kmptraining.migrate.session5.session5_4.screens.main.profile.ProfileScreen
import kotlinx.coroutines.delay
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun MainFlow(onLogout: () -> Unit) {
    val navConfig = SavedStateConfiguration {
        serializersModule = SerializersModule {
            polymorphic(NavKey::class) {
                subclass(MainKey.FeedKey::class, MainKey.FeedKey.serializer())
                subclass(MainKey.PostDetailKey::class, MainKey.PostDetailKey.serializer())
                subclass(MainKey.DiscoverKey::class, MainKey.DiscoverKey.serializer())
                subclass(MainKey.ProfileKey::class, MainKey.ProfileKey.serializer())
                subclass(MainKey.EditProfileKey::class, MainKey.EditProfileKey.serializer())
            }
        }
    }
    val feedStack = rememberNavBackStack(navConfig, MainKey.FeedKey)
    val discoverStack = rememberNavBackStack(navConfig, MainKey.DiscoverKey)
    val profileStack = rememberNavBackStack(navConfig, MainKey.ProfileKey)
    var currentTab by rememberSaveable { mutableStateOf(Tab.FEED) }
    val currentStack = when (currentTab) {
        Tab.FEED -> feedStack
        Tab.DISCOVER -> discoverStack
        Tab.PROFILE -> profileStack
    }
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentTab == Tab.FEED,
                    onClick = { currentTab = Tab.FEED },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Feed") },
                    label = { Text("Feed") }
                )
                NavigationBarItem(
                    selected = currentTab == Tab.DISCOVER,
                    onClick = { currentTab = Tab.DISCOVER },
                    icon = { Icon(Icons.Default.Search, contentDescription = "Discover") },
                    label = { Text("Discover") }
                )
                NavigationBarItem(
                    selected = currentTab == Tab.PROFILE,
                    onClick = { currentTab = Tab.PROFILE },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") }
                )
            }
        }
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier.padding(innerPadding),
            backStack = currentStack,
            onBack = { if (currentStack.size > 1) currentStack.removeLastOrNull() },
            entryProvider = entryProvider {
                entry<MainKey.FeedKey> {
                    FeedScreen(
                        onPostClick = { postId -> feedStack.add(MainKey.PostDetailKey(postId)) }
                    )
                }
                entry<MainKey.PostDetailKey> { key ->
                    PostDetailScreen(
                        postId = key.postId,
                        onBack = { if (feedStack.size > 1) feedStack.removeLastOrNull() else if (discoverStack.size > 1) discoverStack.removeLastOrNull() }
                    )
                }
                entry<MainKey.DiscoverKey> {
                    DiscoverScreen(
                        onPostClick = { postId -> discoverStack.add(MainKey.PostDetailKey(postId)) }
                    )
                }
                entry<MainKey.ProfileKey> {
                    // LaunchedEffect: fetch user data khi vào screen
                    var user by remember { mutableStateOf<UserData?>(null) }
                    LaunchedEffect(user?.id) {
                        delay(500) // giả lập network
                        user = registeredUsers.first()
                    }
                    ProfileScreen(
                        user = user,
                        onLogout = {
                            currentStack.clear()
                            onLogout()
                        },
                        onEditProfile = { profileStack.add(MainKey.EditProfileKey) },
                        onPostClick = { postId ->
                            profileStack.add(MainKey.PostDetailKey(postId))
                        },
                    )
                }
                entry<MainKey.EditProfileKey> {
                    EditProfileScreen(
                        user = registeredUsers.first(),
                        onSave = { },
                        onBack = { profileStack.removeLastOrNull() }
                    )
                }
            }
        )
    }
}