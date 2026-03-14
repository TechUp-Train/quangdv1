package com.example.kmptraining.kmp_session2.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.kmptraining.kmp_session2.data.News
import com.example.kmptraining.kmp_session2.domain.utils.ResponseStatus
import com.example.kmptraining.kmp_session2.presentation.home.component.HomeErrorUI
import com.example.kmptraining.kmp_session2.presentation.home.component.HomeLoadingUI
import com.example.kmptraining.kmp_session2.presentation.home.component.NewsItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(onNewsClick: (News) -> Unit, viewModel: HomeViewModel = koinViewModel()) {
    val news = News(
        id = 12,
        title = "Why Developers Love Kotlin",
        content = "Kotlin’s concise syntax and powerful features make it one of the most loved programming languages today.",
        imageUrl = "https://picsum.photos/id/1057",
        author = "Emma Thompson",
        category = "Technology",
        readDuration = "3 min read"
    )
    val newsState = viewModel.newsState.collectAsStateWithLifecycle().value

    LaunchedEffect(Unit) {
        viewModel.getNews()
    }

    when (newsState) {
        is ResponseStatus.Loading -> { HomeLoadingUI() }

        is ResponseStatus.Error -> { HomeErrorUI() }

        is ResponseStatus.Success -> {
            val newsList = newsState.data
            BoxWithConstraints(
                modifier = Modifier.fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(vertical = 5.dp)
            ) {
                val screenHeight = maxHeight
                LazyColumn(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(maxHeight * 0.02f)
                ) {
                    items(newsList, key = { it.id }) { newsItem ->
                        NewsItem(newsItem, screenHeight * 0.2f, onNewsClick = { onNewsClick(newsItem) })
                    }
                }
            }
        }
    }
}