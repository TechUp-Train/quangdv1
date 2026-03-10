package com.example.composetraining.session5.session5_4.screens.main.feed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetraining.session5.session5_4.data.samplePosts
import com.example.composetraining.session5.session5_4.screens.main.feed.component.PostCard
import com.example.composetraining.ui.theme.ComposeTrainingTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(
    onPostClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Feed") }
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                top = innerPadding.calculateTopPadding() + 8.dp,
                bottom = innerPadding.calculateBottomPadding() + 8.dp,
                start = 16.dp,
                end = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(samplePosts) { post ->
                PostCard(
                    post = post,
                    onClick = { onPostClick(post.id) }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun FeedScreenPreview() {
    ComposeTrainingTheme {
        FeedScreen(onPostClick = {})
    }
}