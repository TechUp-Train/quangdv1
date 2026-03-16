package com.example.kmptraining.kmp_session2.presentation.setting.languageSetting

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kmptraining.composeapp.generated.resources.Res
import kmptraining.composeapp.generated.resources.language
import org.jetbrains.compose.resources.stringResource

@Composable
fun LanguageSettingScreen() {
    Text(
        stringResource(Res.string.language),
        modifier = Modifier.fillMaxSize()
    )
}
