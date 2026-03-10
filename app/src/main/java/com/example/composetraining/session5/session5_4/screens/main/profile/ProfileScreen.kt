package com.example.composetraining.session5.session5_4.screens.main.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetraining.session5.session5_4.data.UserData
import com.example.composetraining.session5.session5_4.data.registeredUsers
import com.example.composetraining.session5.session5_4.data.samplePosts
import com.example.composetraining.session5.session5_4.screens.main.feed.component.PostCard
import com.example.composetraining.ui.theme.ComposeTrainingTheme

@Composable
fun ProfileScreen(
    user: UserData?,
    onLogout: () -> Unit,
    onEditProfile: () -> Unit,
    onPostClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val userPosts = remember(user) {
        if (user == null) emptyList() else samplePosts.filter { it.userId == 1 } // Giả lập id = 1 là current user
    }

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                top = innerPadding.calculateTopPadding() + 24.dp,
                bottom = innerPadding.calculateBottomPadding() + 24.dp
            )
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Avatar Placeholder
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = user?.name?.firstOrNull()?.toString() ?: "?",
                            style = MaterialTheme.typography.displaySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = user?.name ?: "Loading...",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = user?.bio ?: "No bio available",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Stats Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        ProfileStatItem(label = "Posts", value = userPosts.size.toString())
                        ProfileStatItem(label = "Followers", value = "1.2k")
                        ProfileStatItem(label = "Following", value = "348")
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Action Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = onEditProfile,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Edit Profile")
                        }
                        OutlinedButton(
                            onClick = onLogout,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Logout", color = MaterialTheme.colorScheme.error)
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = "Your Posts",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )
                }
            }

            items(userPosts) { post ->
                PostCard(
                    post = post,
                    onClick = { onPostClick(post.id) },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun ProfileStatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    ComposeTrainingTheme {
        ProfileScreen(
            user = registeredUsers.first(),
            onLogout = {},
            onEditProfile = {},
            onPostClick = {}
        )
    }
}