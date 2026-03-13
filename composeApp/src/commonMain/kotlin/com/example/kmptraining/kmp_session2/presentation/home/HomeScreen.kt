package com.example.kmptraining.kmp_session2.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.kmptraining.kmp_session2.data.News
import com.example.kmptraining.kmp_session2.domain.utils.ResponseStatus
import com.example.kmptraining.kmp_session2.presentation.home.component.HomeErrorUI
import com.example.kmptraining.kmp_session2.presentation.home.component.NewsItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onNewsClick: (News) -> Unit
) {
    val news = News(
        id = 1,
        title = "Test news",
        content = "asdufiagsuydhasbdilukjwanoeidfhwaeiodf",
        imageUrl = "",
        author = "",
    )
    val newsState = viewModel.newsState.collectAsStateWithLifecycle().value

    LaunchedEffect(Unit) {
        viewModel.getNews()
    }

    when (newsState) {
        is ResponseStatus.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier.size(50.dp)
                    .background(MaterialTheme.colorScheme.surface),
            )
        }

        is ResponseStatus.Error -> {
            HomeErrorUI()
        }

        is ResponseStatus.Success -> {
            val newsList = newsState.data
            LazyColumn(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                items(newsList, key = { it.id }) { newsItem ->
                    NewsItem(newsItem, onNewsClick = { onNewsClick(newsItem) })
                }
            }

        }
    }
}

@Composable
fun MockHomeScreen() {

}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    MockHomeScreen()
}