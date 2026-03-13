package com.example.kmptraining.migrate.session3.session3_4

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import com.example.kmptraining.migrate.session3.session3_4.component.FormHeader
import com.example.kmptraining.migrate.session3.session3_4.screens.FormContent
import com.example.kmptraining.migrate.session3.session3_4.screens.SubmissionSuccessScreen
import com.example.kmptraining.migrate.common.bg_page
import com.example.kmptraining.migrate.ui.theme.ComposeTrainingTheme
import kotlin.math.max
import kotlin.math.min

/**
 * ⭐⭐⭐⭐ BÀI TẬP 4: Multi-step Registration Form (Advanced)
 *
 * Mô tả: Form đăng ký nhiều bước với complex state management, validation, UDF pattern
 *
 * Steps: Personal Info → Contact → Preferences → Review
 *
 * Key concepts:
 * - @Stable annotation: đánh dấu class "ổn định" → Compose SKIP recompose nếu params không đổi
 * - UDF (Unidirectional Data Flow): State đi xuống, Events đi lên
 * - sealed class FormAction: type-safe events thay vì nhiều callbacks
 * - AnimatedContent: slide animation khi chuyển step
 * - rememberSaveable + custom Saver: form state survive xoay màn hình
 *
 * Bonus (nếu xong sớm):
 * - TODO: [Bonus] Implement custom Saver cho FormState (dùng mapSaver hoặc listSaver)
 * - val formState = rememberSaveable(saver = FormStateSaver) { FormState() }
 */

// ─── State & Actions (UDF pattern) ───────────────────────────────────────────

/**
 * @Stable annotation — tại sao cần?
 *
 * @Stable nói với Compose rằng:
 * 1. Nếu các properties không đổi (theo equals()), class được xem là "stable"
 * 2. Compose CÓ THỂ SKIP recompose nếu toàn bộ params không thay đổi
 *
 * @Stable vs @Immutable:
 * - @Stable: properties có thể thay đổi NHƯNG theo equals() đúng cách
 * - @Immutable: properties KHÔNG BAO GIỜ thay đổi (mạnh hơn @Stable)
 */
@Stable
data class FormState(
    // Step 1: Personal Info
    val firstName: String = "",
    val lastName: String = "",
    val birthYear: String = "",
    // Step 2: Contact
    val email: String = "",
    val phone: String = "",
    val city: String = "",
    // Step 3: Preferences
    val receiveNewsletter: Boolean = false,
    val receiveNotifications: Boolean = true,
    val preferredLanguage: String = "Vietnamese",
    // Navigation
    val currentStep: Int = 0,
    // Validation errors
    val firstNameError: String? = null,
    val lastNameError: String? = null,
    val emailError: String? = null,
    val phoneError: String? = null,
    // Submission
    val isSubmitted: Boolean = false,
)

val FormState.totalSteps: Int get() = 4
val FormState.progress: Float get() = (currentStep + 1).toFloat() / totalSteps.toFloat()
val FormState.stepTitle: String
    get() =
        when (currentStep) {
            0 -> "Personal Info"
            1 -> "Contact Details"
            2 -> "Preferences"
            3 -> "Review & Submit"
            else -> ""
        }

/**
 * sealed class FormAction — type-safe events từ UI lên ViewModel/Host
 *
 * Thay vì nhiều callbacks rời rạc (onFirstNameChange, onNext, onSubmit...)
 * → Dùng 1 callback duy nhất: onAction: (FormAction) -> Unit
 *
 * Lợi ích: API gọn hơn, dễ log, dễ test
 */
sealed class FormAction {
    data class UpdateFirstName(
        val value: String,
    ) : FormAction()

    data class UpdateLastName(
        val value: String,
    ) : FormAction()

    data class UpdateBirthYear(
        val value: String,
    ) : FormAction()

    data class UpdateEmail(
        val value: String,
    ) : FormAction()

    data class UpdatePhone(
        val value: String,
    ) : FormAction()

    data class UpdateCity(
        val value: String,
    ) : FormAction()

    data class UpdateNewsletter(
        val enabled: Boolean,
    ) : FormAction()

    data class UpdateNotifications(
        val enabled: Boolean,
    ) : FormAction()

    data class UpdateLanguage(
        val language: String,
    ) : FormAction()

    data object NextStep : FormAction()

    data object PrevStep : FormAction()

    data object Submit : FormAction()
}

// ─── Business Logic (Reducer) ─────────────────────────────────────────────────

/**
 * Hàm reduce: nhận state hiện tại + action → trả về state mới
 *
 * Pattern: Pure function, không có side effects
 * - Input: (FormState, FormAction) → Output: FormState
 * - Dễ test: chỉ cần verify output state
 */
fun reduceFormState(
    state: FormState,
    action: FormAction,
): FormState =
    when (action) {
        is FormAction.UpdateFirstName -> state.copy(firstName = action.value, firstNameError = null)
        is FormAction.UpdateLastName -> state.copy(lastName = action.value, lastNameError = null)
        is FormAction.UpdateBirthYear -> state.copy(birthYear = action.value)
        is FormAction.UpdateEmail -> state.copy(email = action.value, emailError = null)
        is FormAction.UpdatePhone -> state.copy(phone = action.value, phoneError = null)
        is FormAction.UpdateCity -> state.copy(city = action.value)
        is FormAction.UpdateNewsletter -> state.copy(receiveNewsletter = action.enabled)
        is FormAction.UpdateNotifications -> state.copy(receiveNotifications = action.enabled)
        is FormAction.UpdateLanguage -> state.copy(preferredLanguage = action.language)
        FormAction.NextStep -> {
            val validatedState = validateCurrentStep(state)
            if (validatedState.hasCurrentStepErrors) {
                validatedState
            } else {
                validatedState.copy(currentStep = min(state.currentStep + 1, state.totalSteps - 1))
            }
        }
        FormAction.PrevStep -> state.copy(currentStep = max(state.currentStep - 1, 0))
        FormAction.Submit -> state.copy(isSubmitted = true)
    }

private fun validateCurrentStep(state: FormState): FormState =
    when (state.currentStep) {
        0 ->
            state.copy(
                firstNameError = if (state.firstName.isBlank()) "First name is required" else null,
                lastNameError = if (state.lastName.isBlank()) "Last name is required" else null,
            )
        1 ->
            state.copy(
                emailError = if (!state.email.contains("@")) "Invalid email" else null,
                phoneError = if (state.phone.length < 9) "Phone number too short" else null,
            )
        else -> state
    }

private val FormState.hasCurrentStepErrors: Boolean
    get() =
        when (currentStep) {
            0 -> firstNameError != null || lastNameError != null
            1 -> emailError != null || phoneError != null
            else -> false
        }

// ─── Host Composable (Stateful) ───────────────────────────────────────────────

/**
 * MultiStepFormScreen — stateful host
 *
 * Host giữ state và cung cấp cho FormContent (stateless child)
 * Pattern: State hosting ở level cao nhất cần dùng state
 */
@Composable
fun MultiStepFormScreen(modifier: Modifier = Modifier) {
    var formState by remember { mutableStateOf(FormState()) }
    val onAction: (FormAction) -> Unit = { action ->
        formState = reduceFormState(formState, action)
    }

    if (formState.isSubmitted) {
        SubmissionSuccessScreen(formState = formState)
    } else {
        Scaffold(
            containerColor = bg_page,
            topBar = { FormHeader() },
        ) { contentPadding ->
            FormContent(
                state = formState,
                onAction = onAction,
                modifier = Modifier.padding(contentPadding),
            )
        }
    }
}

// ─── Previews ─────────────────────────────────────────────────────────────────
@Preview(showBackground = true, name = "Multi Step Form - Light")
@Composable
private fun MultiStepFormPreview() {
    ComposeTrainingTheme {
        MultiStepFormScreen()
    }
}

@Preview(
    showBackground = true,
    name = "Multi Step Form - Dark",
    uiMode = UI_MODE_NIGHT_YES,
)
@Composable
private fun MultiStepFormDarkPreview() {
    ComposeTrainingTheme(darkTheme = true) {
        MultiStepFormScreen()
    }
}
