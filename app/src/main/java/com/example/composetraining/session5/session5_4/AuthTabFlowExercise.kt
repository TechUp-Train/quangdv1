package com.example.composetraining.session5.session5_4

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.composetraining.session5.session5_4.screens.auth.AuthFlow
import com.example.composetraining.session5.session5_4.screens.main.MainFlow
import com.example.composetraining.ui.theme.ComposeTrainingTheme

@Composable
fun AuthTabApp() {
    var isAuthenticated by rememberSaveable { mutableStateOf(false) }

    if (isAuthenticated) {
        MainFlow(
            onLogout = { isAuthenticated = false }
        )
    } else {
        AuthFlow(
            onLoginSuccess = { isAuthenticated = true }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AuthTabAppPreview() {
    ComposeTrainingTheme { AuthTabApp() }
}