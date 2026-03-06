package com.example.composetraining.session3.session3_4.component

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.composetraining.session3.session3_4.FormAction
import com.example.composetraining.session3.session3_4.FormState

// ─── Step 3: Preferences ─────────────────────────────────────────────────────

@Composable
fun PreferencesStep(
    state: FormState,
    onAction: (FormAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    // TODO: Implement PreferencesStep
    // - Column(fillMaxSize, verticalScroll, spacedBy=16.dp)
    // - SwitchRow "Receive newsletter" (receiveNewsletter)
    // - SwitchRow "Push notifications" (receiveNotifications)
    // - HorizontalDivider
    // - Text "Preferred language"
    // - listOf("Vietnamese", "English", "Japanese", "Korean").forEach { lang →
    //     Row: RadioButton(selected = state.preferredLanguage == lang, onClick = ...) + Text lang
    //   }
    Box {}
}