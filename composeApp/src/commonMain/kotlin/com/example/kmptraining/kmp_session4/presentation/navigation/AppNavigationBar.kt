package com.example.kmptraining.kmp_session4.presentation.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import kmptraining.composeapp.generated.resources.Res
import kmptraining.composeapp.generated.resources.ic_home
import kmptraining.composeapp.generated.resources.ic_home_selected
import kmptraining.composeapp.generated.resources.ic_profile
import kmptraining.composeapp.generated.resources.ic_profile_selected
import kmptraining.composeapp.generated.resources.ic_search
import kmptraining.composeapp.generated.resources.ic_search_selected
import org.jetbrains.compose.resources.painterResource

@Composable
fun AppNavigationBar(
    currentKey: Any?,
    onNavigate: (AppNavigationKey) -> Unit
) {
    val isBottomBarVisible = currentKey is AppNavigationKey.Home ||
            currentKey is AppNavigationKey.Explore ||
            currentKey is AppNavigationKey.Profile

    if (isBottomBarVisible) {
        NavigationBar {
            NavigationBarItem(
                selected = currentKey is AppNavigationKey.Home,
                onClick = {
                    if (currentKey !is AppNavigationKey.Home) {
                        onNavigate(AppNavigationKey.Home)
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(if (currentKey is AppNavigationKey.Home) Res.drawable.ic_home_selected else Res.drawable.ic_home),
                        contentDescription = "Home"
                    )
                },
                label = { Text("Home") }
            )

            NavigationBarItem(
                selected = currentKey is AppNavigationKey.Explore,
                onClick = {
                    if (currentKey !is AppNavigationKey.Explore) {
                        onNavigate(AppNavigationKey.Explore)
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(if (currentKey is AppNavigationKey.Explore) Res.drawable.ic_search_selected else Res.drawable.ic_search),
                        contentDescription = "Explore"
                    )
                },
                label = { Text("Explore") }
            )

            NavigationBarItem(
                selected = currentKey is AppNavigationKey.Profile,
                onClick = {
                    if (currentKey !is AppNavigationKey.Profile) {
                        onNavigate(AppNavigationKey.Profile)
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(if (currentKey is AppNavigationKey.Profile) Res.drawable.ic_profile_selected else Res.drawable.ic_profile),
                        contentDescription = "Profile"
                    )
                },
                label = { Text("Profile") }
            )
        }
    }
}
