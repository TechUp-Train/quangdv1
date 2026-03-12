package com.example.kmptraining.migrate.session3.session3_4.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ValidatedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    errorMessage: String?,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    icon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    // - OutlinedTextField với isError = errorMessage != null
    // - supportingText = errorMessage?.let { { Text(it) } }
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = icon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        isError = errorMessage != null,
        supportingText = errorMessage?.let { { Text(it) } },
        maxLines = 1,
        shape = RoundedCornerShape(10.dp),
        colors =
            OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.LightGray,
                errorBorderColor = Color.Red,
            ),
        modifier = modifier,
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
        keyboardType = KeyboardType.Number,
    )
}
