package com.example.composetraining.session5.session5_2.screens.explore

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetraining.ui.theme.ComposeTrainingTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    val popularTopics = listOf("Jetpack Compose", "Kotlin", "Android 15", "Material Design", "Coroutines")

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Explore") })
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Search articles...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                singleLine = true
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Button(
                onClick = { if (searchQuery.isNotBlank()) onSearch(searchQuery) },
                modifier = Modifier.align(Alignment.End),
                enabled = searchQuery.isNotBlank()
            ) {
                Text("Search")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Popular Topics",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                items(popularTopics) { topic ->
                    ListItem(
                        headlineContent = { Text(topic) },
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        trailingContent = {
                            TextButton(onClick = { onSearch(topic) }) {
                                Text("Explore")
                            }
                        }
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExploreScreenPreview() {
    ComposeTrainingTheme {
        ExploreScreen(onSearch = {})
    }
}
