package com.example.composetraining.session3.session3_4.component

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun ValidatedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    errorMessage: String?,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    // TODO: Implement ValidatedTextField
    // - OutlinedTextField với isError = errorMessage != null
    // - supportingText = errorMessage?.let { { Text(it) } }
    Box {}
}