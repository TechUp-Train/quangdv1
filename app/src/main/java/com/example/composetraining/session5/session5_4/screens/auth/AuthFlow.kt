package com.example.composetraining.session5.session5_4.screens.auth

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.composetraining.session5.session5_4.route.AuthKey

@Composable
fun AuthFlow(onLoginSuccess: () -> Unit) {
    val backStack = rememberNavBackStack(AuthKey.Login)
    NavDisplay(
        backStack = backStack,
        onBack = { if (backStack.size > 1) backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<AuthKey.Login> {
                LoginScreen(
                    onLogin = onLoginSuccess,
                    onRegister = { backStack.add(AuthKey.Register) },
                    onForgotPassword = { backStack.add(AuthKey.ForgotPassword) }
                )
            }
            entry<AuthKey.Register> { RegisterScreen(onBack = { backStack.removeLastOrNull() }) }
            entry<AuthKey.ForgotPassword> {
                ForgotPasswordScreen(onBack = { backStack.removeLastOrNull() })
            }
        }
    )
}