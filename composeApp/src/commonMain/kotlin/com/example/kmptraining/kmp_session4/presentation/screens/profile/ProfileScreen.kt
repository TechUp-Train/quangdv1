package com.example.kmptraining.kmp_session4.presentation.screens.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.kmptraining.kmp_session4.domain.model.UserRepoModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = koinViewModel(),
    onRepoClick: (UserRepoModel) -> Unit
) {
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(onRepoClick = {})
}