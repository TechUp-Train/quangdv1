package com.example.kmptraining.kmp_session2.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.kmptraining.kmp_session2.presentation.navigation.HomeNavigationKey
import com.example.kmptraining.kmp_session2.presentation.navigation.SettingNavigationKey
import kmptraining.composeapp.generated.resources.Res
import kmptraining.composeapp.generated.resources.ic_home
import kmptraining.composeapp.generated.resources.ic_home_selected
import kmptraining.composeapp.generated.resources.ic_setting
import kmptraining.composeapp.generated.resources.ic_setting_selected

@Composable
fun BaseBottomNavigationBar(
    backStack: NavBackStack<NavKey>,
    currentScreen: NavKey?,
    modifier: Modifier = Modifier
) {
    val borderColor = MaterialTheme.colorScheme.secondary

    NavigationBar(
        modifier = modifier
            .drawBehind {
                val strokeWidth = 1.dp.toPx()
                drawLine(
                    color = borderColor,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = strokeWidth,
                )
            },
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        BaseBottomNavigationBarItem(
            selected = currentScreen is HomeNavigationKey.HomeKey,
            onClick = {
                if (currentScreen !is HomeNavigationKey.HomeKey) {
                    backStack.clear()
                    backStack.add(HomeNavigationKey.HomeKey)
                }
            },
            icon = Res.drawable.ic_home,
            selectedIcon = Res.drawable.ic_home_selected,
            label = "Home"
        )
        BaseBottomNavigationBarItem(
            selected = currentScreen is SettingNavigationKey.SettingKey,
            onClick = {
                if (currentScreen !is SettingNavigationKey.SettingKey) {
                    backStack.clear()
                    backStack.add(SettingNavigationKey.SettingKey)
                }
            },
            icon = Res.drawable.ic_setting,
            selectedIcon = Res.drawable.ic_setting_selected,
            label = "Settings"
        )
    }
}