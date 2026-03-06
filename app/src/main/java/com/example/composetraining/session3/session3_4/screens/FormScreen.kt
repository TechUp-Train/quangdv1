package com.example.composetraining.session3.session3_4.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.composetraining.session3.session3_4.FormAction
import com.example.composetraining.session3.session3_4.FormState

// ─── Stateless Form Content (UDF Consumer) ───────────────────────────────────

/**
 * FormContent — stateless, nhận state + onAction
 *
 * Đây là điểm áp dụng UDF:
 * - state goes down (nhận từ host)
 * - events go up (gửi onAction lên host)
 */
@Composable
fun FormContent(
    state: FormState,
    onAction: (FormAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    // TODO: Implement FormContent
    // - Column(fillMaxSize, padding=16.dp)
    // - FormHeader(state)
    // - Spacer(16.dp)
    // - LinearProgressIndicator(progress = state.progress, fillMaxWidth)
    // - Spacer(4.dp) + Text "Step ${currentStep+1} of ${totalSteps}: ${stepTitle}" (primary)
    // - Spacer(24.dp)
    // - AnimatedContent(targetState = state.currentStep,
    //       transitionSpec = { // slide từ phải vào nếu đi tới, từ trái vào nếu đi lùi
    //           val direction = if (targetState > initialState) 1 else -1
    //           slideInHorizontally { it * direction } togetherWith slideOutHorizontally { it * -direction }
    //       },
    //       modifier = Modifier.weight(1f)
    //   ) { step → when(step) { 0 → PersonalInfoStep, 1 → ContactStep, 2 → PreferencesStep, 3 → ReviewStep } }
    // - FormNavigationButtons(state, onAction)
    Box {}
}