package com.example.kmptraining.kmp_session4.presentation.screens.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.kmptraining.kmp_session4.core.utils.ResponseStatus
import com.example.kmptraining.kmp_session4.domain.model.RepoModel
import com.example.kmptraining.kmp_session4.presentation.components.ErrorState
import com.example.kmptraining.kmp_session4.presentation.components.LoadingState
import com.example.kmptraining.kmp_session4.presentation.screens.explore.components.ExploreSearchBar
import com.example.kmptraining.kmp_session4.presentation.screens.explore.components.SearchRepoItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ExploreScreen(
    exploreViewModel: ExploreViewModel = koinViewModel<ExploreViewModel>(),
    onRepoClick: (RepoModel) -> Unit
) {
    val searchText by exploreViewModel.searchText.collectAsStateWithLifecycle()
    val searchResults by exploreViewModel.searchResults.collectAsStateWithLifecycle()
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0D1117),
                        Color(0xFF161B22)
                    )
                )
            )
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ExploreSearchBar(
                searchText = searchText,
                keyboardController = keyboardController,
                onSearchQueryChanged = { exploreViewModel.onSearchQueryChanged(it) }
            )

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                when (searchResults) {
                    is ResponseStatus.Loading -> { item { LoadingState() } }

                    is ResponseStatus.Error -> {
                        item {
                            ErrorState(
                                message = (searchResults as ResponseStatus.Error).message,
                                onRetry = {
                                    exploreViewModel.onSearchQueryChanged("")

                                }
                            )
                        }
                    }

                    is ResponseStatus.Success -> {
                        val data = (searchResults as ResponseStatus.Success).data
                        if (data.isEmpty() && searchText.isNotEmpty()) {
                            item {
                                Box(
                                    modifier = Modifier.fillParentMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "No results found for \"$searchText\"",
                                        color = Color.Gray
                                    )
                                }
                            }
                        } else {
                            items(data) { repo ->
                                SearchRepoItem(repo = repo, onClick = { onRepoClick(repo) })
                                HorizontalDivider(thickness = 0.5.dp, color = Color(0xFF30363D))
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ExploreScreenPreview() {
    ExploreScreen(onRepoClick = {})
}