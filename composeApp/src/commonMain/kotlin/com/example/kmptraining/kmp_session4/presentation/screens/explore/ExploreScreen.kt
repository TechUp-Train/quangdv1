package com.example.kmptraining.kmp_session4.presentation.screens.explore

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.kmptraining.kmp_session4.domain.model.PublicRepoModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ExploreScreen(
    exploreViewModel: ExploreViewModel = koinViewModel<ExploreViewModel>(),
    onRepoClick: (PublicRepoModel) -> Unit
) {
}

@Preview(showBackground = true)
@Composable
fun ExploreScreenPreview() {
    ExploreScreen(onRepoClick = {})
}