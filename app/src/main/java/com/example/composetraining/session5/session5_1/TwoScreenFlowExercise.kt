package com.example.composetraining.session5.session5_1

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.composetraining.session5.session5_1.route.HomeKey
import com.example.composetraining.session5.session5_1.route.WelcomeKey
import com.example.composetraining.session5.session5_1.screens.HomeScreen
import com.example.composetraining.session5.session5_1.screens.WelcomeScreen
import com.example.composetraining.ui.theme.ComposeTrainingTheme

/**
 * ⭐ BÀI TẬP 1: 2-Screen Flow (Easy — 30 phút)
 *
 * Học Navigation 3: Back stack là List<NavKey>, không phải NavController
 *
 * Yêu cầu:
 * - Định nghĩa 2 keys: @Serializable data object WelcomeKey : NavKey
 * - Back stack: rememberNavBackStack(WelcomeKey)
 * - NavDisplay với entryProvider mapping key → screen
 * - Welcome screen: Button "Bắt đầu" → backStack.add(HomeKey)
 * - Home screen: Button "Đăng xuất" → backStack.clear(); backStack.add(WelcomeKey)
 * - Back button (hardware/gesture) từ Home → Welcome hoạt động đúng
 */

@Composable
fun TwoScreenFlowApp() {
    val backStack = rememberNavBackStack(WelcomeKey)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<WelcomeKey> {
                WelcomeScreen(onGetStarted = { backStack.add(HomeKey) })
            }
            entry<HomeKey> {
                HomeScreen(onLogout = {
                    backStack.clear()
                    backStack.add(WelcomeKey)
                })
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun TwoScreenFlowPreview() {
    ComposeTrainingTheme { TwoScreenFlowApp() }
}
