package com.example.composetraining.session3.session3_4.component.persionalInfoStep

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.composetraining.R
import com.example.composetraining.session3.session3_4.FormAction
import com.example.composetraining.session3.session3_4.FormState
import com.example.composetraining.session3.session3_4.component.ValidatedTextField

// ─── Step 1: Personal Info ────────────────────────────────────────────────────
@Composable
fun PersonalInfoStep(
    state: FormState,
    onAction: (FormAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    // - Column(fillMaxSize, verticalScroll, spacedBy=12.dp)
    // - ValidatedTextField firstName (error = state.firstNameError)
    // - ValidatedTextField lastName (error = state.lastNameError)
    // - OutlinedTextField birthYear (optional, keyboardType = Number)
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ValidatedTextField(
            value = state.firstName,
            onValueChange = { onAction(FormAction.UpdateFirstName(it)) },
            label = stringResource(R.string.first_name),
            errorMessage = state.firstNameError ?: stringResource(
                R.string.invalid,
                stringResource(R.string.first_name)
            ),
        )
        ValidatedTextField(
            value = state.lastName,
            onValueChange = { onAction(FormAction.UpdateLastName(it)) },
            label = stringResource(R.string.last_name),
            errorMessage = state.lastNameError ?: stringResource(
                R.string.invalid,
                stringResource(R.string.last_name)
            ),
        )
        OutlinedTextField(
            value = state.birthYear,
            onValueChange = { onAction(FormAction.UpdateBirthYear(it)) },
            label = { Text(stringResource(R.string.birth_year)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            trailingIcon = {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = null)
            }
        )
    }
}