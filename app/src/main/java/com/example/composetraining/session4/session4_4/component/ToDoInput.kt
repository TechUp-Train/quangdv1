package com.example.composetraining.session4.session4_4.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetraining.ui.theme.ComposeTrainingTheme

// ─── Add Todo Input ───────────────────────────────────────────────────────────

@Composable
fun AddTodoInput(
    value: String,
    onValueChange: (String) -> Unit,
    onAdd: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // - Row(fillMaxWidth, CenterVertically, spacedBy=8.dp)
    // - OutlinedTextField(weight(1f), singleLine, placeholder = "Add new task...")
    // - IconButton(onClick = onAdd, enabled = value.isNotBlank())
    //   Icon = Check nếu có text, Add nếu không; tint = primary nếu enabled, outline nếu không
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f),
            singleLine = true,
            placeholder = { Text("Add new task...") }
        )
        IconButton(
            onClick = onAdd,
            enabled = value.isNotBlank(),
        ) {
            Icon(
                imageVector = if (value.isNotBlank()) Icons.Default.Check else Icons.Default.Add,
                contentDescription = "Add",
                tint = if (value.isNotBlank()) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddTodoInputEmptyPreview() {
    ComposeTrainingTheme {
        AddTodoInput(
            value = "",
            onValueChange = {},
            onAdd = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AddTodoInputWithTextPreview() {
    ComposeTrainingTheme {
        AddTodoInput(
            value = "Buy milk",
            onValueChange = {},
            onAdd = {}
        )
    }
}