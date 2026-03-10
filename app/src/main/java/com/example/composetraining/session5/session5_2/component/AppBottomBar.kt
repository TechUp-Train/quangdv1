package com.example.composetraining.session5.session5_2.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.composetraining.session5.session5_2.route.NavigationTab

@Composable
fun AppBottomBar(selectedTab: NavigationTab, onTabSelect: (NavigationTab) -> Unit) {
    NavigationBar {
        NavigationBarItem(
            selected = selectedTab == NavigationTab.HOME,
            onClick = { onTabSelect(NavigationTab.HOME) },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = selectedTab == NavigationTab.EXPLORE,
            onClick = { onTabSelect(NavigationTab.EXPLORE) },
            icon = { Icon(Icons.Default.Search, contentDescription = "Explore") },
            label = { Text("Explore") }
        )
        NavigationBarItem(
            selected = selectedTab == NavigationTab.PROFILE,
            onClick = { onTabSelect(NavigationTab.PROFILE) },
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppBottomBarPreview() {
    AppBottomBar(selectedTab = NavigationTab.HOME, onTabSelect = {})
}
