package com.example.kmptraining.kmp_session2.presentation.newsDetail

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.kmptraining.kmp_session2.domain.utils.ResponseStatus
import com.example.kmptraining.kmp_session2.presentation.components.BaseTopAppBar
import com.example.kmptraining.kmp_session2.presentation.newsDetail.component.NewsDetailUI
import com.example.kmptraining.kmp_session2.presentation.newsDetail.component.NewsErrorUI
import com.example.kmptraining.kmp_session2.presentation.newsDetail.component.NewsLoadingUI
import kmptraining.composeapp.generated.resources.Res
import kmptraining.composeapp.generated.resources.ic_back
import kmptraining.composeapp.generated.resources.ic_bookmark
import kmptraining.composeapp.generated.resources.ic_share
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailScreen(
    newsId: Int,
    onBack: () -> Unit,
    viewModel: NewsDetailViewModel = koinViewModel()
) {
    LaunchedEffect(newsId) {
        viewModel.fetchNews(newsId)
    }
    Scaffold(
        topBar = {
            BaseTopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_back),
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_bookmark),
                            contentDescription = "Bookmark"
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_share),
                            contentDescription = "Share"
                        )
                    }
                },
                scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
            )
        }
    ) { innerPadding ->
        when (val newsState = viewModel.newsState.collectAsStateWithLifecycle().value) {
            is ResponseStatus.Loading -> {
                NewsLoadingUI()
            }

            is ResponseStatus.Error -> {
                NewsErrorUI()
            }

            is ResponseStatus.Success -> {
                val newsData = newsState.data
                NewsDetailUI(
                    newsData,
                    modifier = Modifier.padding(innerPadding),
                )
            }
        }
    }
}

