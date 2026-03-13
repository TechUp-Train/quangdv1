package com.example.kmptraining.migrate.base

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BaseListScreen(list: List<String>, onClick: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
            .padding(5.dp),
    ) {
        items(list.size) { index ->
            val screenName = list[index]
            OutlinedButton(
                modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = androidx.compose.material3.MaterialTheme.colorScheme.primary,
                    contentColor = androidx.compose.material3.MaterialTheme.colorScheme.onPrimary
                ),
                onClick = { onClick(screenName) },
            ) { Text(text = screenName) }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BaseListScreenPreview() {
    BaseListScreen(list = listOf("Session 1", "Session 2", "Session 3")) {}
}