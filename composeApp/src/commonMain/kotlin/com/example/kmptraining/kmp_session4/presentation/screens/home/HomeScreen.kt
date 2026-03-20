package com.example.kmptraining.kmp_session4.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.kmptraining.kmp_session4.core.utils.ResponseStatus
import com.example.kmptraining.kmp_session4.domain.model.PublicRepoModel
import com.example.kmptraining.kmp_session4.presentation.components.ErrorState
import com.example.kmptraining.kmp_session4.presentation.components.LoadingState
import com.example.kmptraining.kmp_session4.presentation.screens.home.components.HomeSearchBar
import com.example.kmptraining.kmp_session4.presentation.screens.home.components.HomeSectionHeader
import com.example.kmptraining.kmp_session4.presentation.screens.home.components.RepoItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = koinViewModel(),
    onSearchRepos: () -> Unit,
    onRepoClick: (PublicRepoModel) -> Unit
) {
    val publicReposState = homeViewModel.publicRepos.collectAsStateWithLifecycle().value

    when (publicReposState) {
        is ResponseStatus.Loading -> {
            LoadingState()
        }
        is ResponseStatus.Error -> {
            ErrorState(
                message = publicReposState.message,
                onRetry = { homeViewModel.fetchPublicRepos() }
            )
        }
        is ResponseStatus.Success -> {
            val repos = publicReposState.data
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF0D1B2A),
                                Color(0xFF1B263B)
                            )
                        )
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Explore GitHub",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    HomeSearchBar(onClick = onSearchRepos)

                    Spacer(modifier = Modifier.height(24.dp))

                    HomeSectionHeader()

                    Spacer(modifier = Modifier.height(12.dp))

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(repos, key = { it.id }) { repo ->
                            RepoItem(repo = repo, onClick = { onRepoClick(repo) })
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(onSearchRepos = {}, onRepoClick = {})
}