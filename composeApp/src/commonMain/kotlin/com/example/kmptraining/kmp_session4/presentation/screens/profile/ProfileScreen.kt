package com.example.kmptraining.kmp_session4.presentation.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kmptraining.kmp_session4.core.utils.ResponseStatus
import com.example.kmptraining.kmp_session4.domain.model.RepoModel
import com.example.kmptraining.kmp_session4.presentation.components.ErrorState
import com.example.kmptraining.kmp_session4.presentation.components.LoadingState
import com.example.kmptraining.kmp_session4.presentation.screens.profile.components.ProfileHeader
import com.example.kmptraining.kmp_session4.presentation.screens.profile.components.ProfileRepoItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = koinViewModel(),
    onRepoClick: (RepoModel) -> Unit
) {
    val userState = profileViewModel.userState.collectAsState().value
    val reposState = profileViewModel.reposState.collectAsState().value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0D1117), // GitHub dark background
                        Color(0xFF161B22)
                    )
                )
            )
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Profile Header Section
            item {
                when (userState) {
                    is ResponseStatus.Loading -> LoadingState()
                    is ResponseStatus.Error -> ErrorState(
                        message = userState.message,
                        onRetry = { profileViewModel.fetchProfileData() }
                    )
                    is ResponseStatus.Success -> ProfileHeader(user = userState.data)
                }
            }

            // Popular Repositories Header
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Popular Repositories",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    )
                    TextButton(onClick = { /* View all action */ }) {
                        Text(
                            text = "View all",
                            color = Color(0xFF4DA3FF),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            // Repositories List
            when (reposState) {
                is ResponseStatus.Loading -> {
                    item { LoadingState() }
                }

                is ResponseStatus.Error -> {
                    item {
                        ErrorState(
                            message = reposState.message,
                            onRetry = { profileViewModel.fetchProfileData() }
                        )
                    }
                }

                is ResponseStatus.Success -> {
                    items(reposState.data) { repo ->
                        Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                            ProfileRepoItem(repo = repo, onClick = { onRepoClick(repo) })
                        }
                    }
                }
            }

            // Bottom spacer
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(onRepoClick = {})
}