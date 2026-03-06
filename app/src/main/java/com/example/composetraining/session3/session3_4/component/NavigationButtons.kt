package com.example.composetraining.session3.session3_4.component

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.composetraining.session3.session3_4.FormAction
import com.example.composetraining.session3.session3_4.FormState

// ─── Navigation Buttons ───────────────────────────────────────────────────────

@Composable
fun FormNavigationButtons(
    state: FormState,
    onAction: (FormAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    // TODO: Implement FormNavigationButtons
    // - val isLastStep = state.currentStep == state.totalSteps - 1
    // - Row(fillMaxWidth, spacedBy=12.dp, padding top=16.dp)
    // - Nếu currentStep > 0: OutlinedButton "Back" (weight(1f)) → onAction(PrevStep)
    // - Button "Next" hoặc "Submit" (weight(1f)) → onAction(NextStep) hoặc onAction(Submit)
    Box {}
}