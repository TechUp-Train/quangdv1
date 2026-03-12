package com.example.kmptraining.migrate.session5.session5_4.screens.auth

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.example.kmptraining.migrate.session5.session5_4.route.AuthKey
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun AuthFlow(onLoginSuccess: () -> Unit) {
    val navConfig = SavedStateConfiguration {
        serializersModule = SerializersModule {
            polymorphic(NavKey::class) {
                subclass(AuthKey.Login::class, AuthKey.Login.serializer())
                subclass(AuthKey.Register::class, AuthKey.Register.serializer())
                subclass(AuthKey.ForgotPassword::class, AuthKey.ForgotPassword.serializer())
            }
        }
    }
    val backStack = rememberNavBackStack(navConfig, AuthKey.Login)
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