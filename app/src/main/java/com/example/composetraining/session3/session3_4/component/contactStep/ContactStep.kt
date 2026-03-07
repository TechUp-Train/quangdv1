package com.example.composetraining.session3.session3_4.component.contactStep

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
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

// ─── Step 2: Contact ──────────────────────────────────────────────────────────

@Composable
fun ContactStep(
    state: FormState,
    onAction: (FormAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    // - Column(fillMaxSize, verticalScroll, spacedBy=12.dp)
    // - ValidatedTextField email (error = emailError, keyboardType = Email)
    // - ValidatedTextField phone (error = phoneError, keyboardType = Phone)
    // - OutlinedTextField city (optional)
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ValidatedTextField(
            value = state.email,
            onValueChange = { onAction(FormAction.UpdateEmail(it)) },
            label = stringResource(R.string.email),
            errorMessage = state.emailError,
            keyboardType = KeyboardType.Email,
        )
        ValidatedTextField(
            value = state.phone,
            onValueChange = { onAction(FormAction.UpdatePhone(it)) },
            label = stringResource(R.string.phone),
            errorMessage = state.phoneError,
            keyboardType = KeyboardType.Number,
        )
        OutlinedTextField(
            value = state.city,
            onValueChange = { onAction(FormAction.UpdateCity(it)) },
            label = { Text(stringResource(R.string.city)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            leadingIcon = { Icon(imageVector = Icons.Default.Place, contentDescription = null) }
        )
    }
}