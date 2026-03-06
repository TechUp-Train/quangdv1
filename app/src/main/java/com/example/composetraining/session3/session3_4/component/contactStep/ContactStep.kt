package com.example.composetraining.session3.session3_4.component.contactStep

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.composetraining.session3.session3_4.FormAction
import com.example.composetraining.session3.session3_4.FormState

// ─── Step 2: Contact ──────────────────────────────────────────────────────────

@Composable
fun ContactStep(
    state: FormState,
    onAction: (FormAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    // TODO: Implement ContactStep
    // - Column(fillMaxSize, verticalScroll, spacedBy=12.dp)
    // - ValidatedTextField email (error = emailError, keyboardType = Email)
    // - ValidatedTextField phone (error = phoneError, keyboardType = Phone)
    // - OutlinedTextField city (optional)
    Box {}
}