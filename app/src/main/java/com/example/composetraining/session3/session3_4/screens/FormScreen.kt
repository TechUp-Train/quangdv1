package com.example.composetraining.session3.session3_4.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.composetraining.session3.session3_4.FormAction
import com.example.composetraining.session3.session3_4.FormState
import com.example.composetraining.session3.session3_4.component.FormNavigationButtons
import com.example.composetraining.session3.session3_4.component.contactStep.ContactStep
import com.example.composetraining.session3.session3_4.component.persionalInfoStep.PersonalInfoStep
import com.example.composetraining.session3.session3_4.component.preferenceStep.PreferencesStep
import com.example.composetraining.session3.session3_4.component.reviewStep.ReviewStep
import com.example.composetraining.session3.session3_4.progress
import com.example.composetraining.session3.session3_4.stepTitle
import com.example.composetraining.session3.session3_4.totalSteps

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
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LinearProgressIndicator(
            progress = { state.progress },
            modifier = Modifier.fillMaxWidth(),
            color = ProgressIndicatorDefaults.linearColor,
            trackColor = ProgressIndicatorDefaults.linearTrackColor,
            strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Step ${state.currentStep + 1} of ${state.totalSteps}: ${state.stepTitle}",
            style = TextStyle(
                fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
            )
        )
        Spacer(Modifier.height(24.dp))
        AnimatedContent(
            targetState = state.currentStep,
            transitionSpec = {
                val direction = if (targetState > initialState) 1 else -1
                slideInHorizontally { it * direction } togetherWith slideOutHorizontally { it * -direction }
            },
            modifier = Modifier.weight(1f)
        ) { step ->
            when (step) {
                0 -> PersonalInfoStep(state, onAction)
                1 -> ContactStep(state, onAction)
                2 -> PreferencesStep(state, onAction)
                3 -> ReviewStep(state)
            }
        }
        FormNavigationButtons(state, onAction)
    }
}