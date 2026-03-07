package com.example.composetraining.session3.session3_4.component

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ValidatedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    errorMessage: String?,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    // - OutlinedTextField với isError = errorMessage != null
    // - supportingText = errorMessage?.let { { Text(it) } }
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, modifier = modifier) },
        isError = errorMessage != null,
        supportingText = errorMessage?.let { { Text(it) } },
        maxLines = 1,
    )
}

@Preview(showBackground = true)
@Composable
private fun ValidatedTextFieldPreview() {
    ValidatedTextField(
        value = "Hello",
        onValueChange = {},
        label = "Label",
        errorMessage = null,
        keyboardType = KeyboardType.Number
    )
}