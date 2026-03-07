package com.example.composetraining.session3.session3_4

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.composetraining.common.bg_page
import com.example.composetraining.session3.session3_4.screens.FormContent
import com.example.composetraining.session3.session3_4.component.FormHeader
import com.example.composetraining.session3.session3_4.screens.SubmissionSuccessScreen
import com.example.composetraining.ui.theme.ComposeTrainingTheme

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
    get() = when (currentStep) {
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
    data class UpdateFirstName(val value: String) : FormAction()
    data class UpdateLastName(val value: String) : FormAction()
    data class UpdateBirthYear(val value: String) : FormAction()
    data class UpdateEmail(val value: String) : FormAction()
    data class UpdatePhone(val value: String) : FormAction()
    data class UpdateCity(val value: String) : FormAction()
    data class UpdateNewsletter(val enabled: Boolean) : FormAction()
    data class UpdateNotifications(val enabled: Boolean) : FormAction()
    data class UpdateLanguage(val language: String) : FormAction()
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
fun reduceFormState(state: FormState, action: FormAction): FormState {
    // TODO: Implement reduceFormState
    // Với mỗi FormAction, trả về state.copy(...) phù hợp:
    // - UpdateFirstName → copy(firstName = action.value, firstNameError = null)
    // - UpdateEmail → copy(email = action.value, emailError = null)
    // - NextStep → validate trước (gọi validateCurrentStep), nếu có lỗi → trả lại state có lỗi
    //              nếu OK → copy(currentStep = min(currentStep + 1, totalSteps - 1))
    // - PrevStep → copy(currentStep = max(currentStep - 1, 0))
    // - Submit → copy(isSubmitted = true)
    // GỢI Ý: Dùng when (action) { is UpdateFirstName → ... }
    TODO("Not yet implemented")
}

private fun validateCurrentStep(state: FormState): FormState {
    // TODO: Validate dựa theo currentStep:
    // - Step 0: kiểm tra firstName và lastName không blank
    // - Step 1: kiểm tra email có "@", phone.length >= 9
    // - Các step khác: không cần validate
    // Trả về state.copy(xFirstNameError, lastNameError, emailError, phoneError)
    TODO("Not yet implemented")
}

private val FormState.hasCurrentStepErrors: Boolean
    get() = false  // TODO: Trả về true nếu step hiện tại có lỗi

// ─── Host Composable (Stateful) ───────────────────────────────────────────────

/**
 * MultiStepFormScreen — stateful host
 *
 * Host giữ state và cung cấp cho FormContent (stateless child)
 * Pattern: State hosting ở level cao nhất cần dùng state
 */
@Composable
fun MultiStepFormScreen(modifier: Modifier = Modifier) {
    // TODO: Implement MultiStepFormScreen
    var formState by remember { mutableStateOf(FormState()) }
    val onAction: (FormAction) -> Unit = {
//        action → formState = reduceFormState(formState, action)
    }
    // 3. Kiểm tra formState.isSubmitted:
    //    → true: SubmissionSuccessScreen(formState)
    //    → false: FormContent(formState, onAction)
    Scaffold(
        containerColor = bg_page,
        topBar = { FormHeader() }
    ) { contentPadding ->
        FormContent(
            state = formState,
            onAction = onAction,
            modifier = Modifier.padding(contentPadding)
        )
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
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun MultiStepFormDarkPreview() {
    ComposeTrainingTheme(darkTheme = true) {
        MultiStepFormScreen()
    }
}