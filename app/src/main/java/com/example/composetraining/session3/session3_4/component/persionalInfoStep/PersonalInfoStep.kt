package com.example.composetraining.session3.session3_4.component.persionalInfoStep

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.composetraining.session3.session3_4.FormAction
import com.example.composetraining.session3.session3_4.FormState

// ─── Step 1: Personal Info ────────────────────────────────────────────────────
@Composable
fun PersonalInfoStep(
    state: FormState,
    onAction: (FormAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    // TODO: Implement PersonalInfoStep
    // - Column(fillMaxSize, verticalScroll, spacedBy=12.dp)
    // - ValidatedTextField firstName (error = state.firstNameError)
    // - ValidatedTextField lastName (error = state.lastNameError)
    // - OutlinedTextField birthYear (optional, keyboardType = Number)
    Box {}
}