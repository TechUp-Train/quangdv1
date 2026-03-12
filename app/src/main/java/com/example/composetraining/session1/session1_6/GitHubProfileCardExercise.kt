package com.example.composetraining.session1.session1_6

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetraining.session1.session1_6.component.LanguageChipsSection
import com.example.composetraining.session1.session1_6.component.PinnedReposRow
import com.example.composetraining.session1.session1_6.component.ProfileHeader
import com.example.composetraining.session1.session1_6.component.ProfileStats
import com.example.composetraining.session1.session1_6.data.GitHubProfile
import com.example.composetraining.session1.session1_6.data.sampleProfile
import com.example.composetraining.ui.theme.ComposeTrainingTheme

/**
 * ⭐⭐⭐⭐⭐ BONUS (Dành cho Nguyễn Quang Minh)
 *
 * GitHub Profile Card — Advanced Modifier & Composition
 *
 * Yêu cầu:
 * 1. Avatar với GRADIENT BORDER (Brush.linearGradient)
 * 2. Stats row: Repos / Stars / Followers — Divider giữa các stat
 * 3. Language chips: FlowRow layout (dùng FlowRow từ accompanist HOẶC
 *    tự implement wrap bằng Layout composable)
 * 4. Pinned repos: 2 cards EQUAL HEIGHT (IntrinsicSize.Max)
 *    + graphicsLayer scale effect khi "pressed" (mock với alpha)
 * 5. Tất cả component phải có modifier param
 * 6. 3 @Preview: Portrait / Large font / Dark mode
 *
 * Khái niệm nâng cao:
 * - Brush.linearGradient cho gradient border
 * - graphicsLayer { scaleX; scaleY; alpha } cho visual effects
 * - Custom Layout composable (nếu tự implement FlowRow)
 * - IntrinsicSize.Max + fillMaxHeight
 *
 * NOTE: File này là SCAFFOLD — các TODO là phần học viên tự implement
 */

// ─── Main Component ──────────────────────────────────────────────────────────

@Composable
fun GitHubProfileCard(
    profile: GitHubProfile,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            // 1. Header: Avatar + Name + Bio
            ProfileHeader(
                username = profile.username,
                displayName = profile.displayName,
                bio = profile.bio,
                location = profile.location,
            )

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(16.dp))

            // 2. Stats row
            ProfileStats(
                repoCount = profile.repoCount,
                starCount = profile.starCount,
                followerCount = profile.followerCount,
            )

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(16.dp))

            // 3. Languages
            Text(
                text = "Languages",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(modifier = Modifier.height(8.dp))
            LanguageChipsSection(languages = profile.languages)

            if (profile.pinnedRepos.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(16.dp))

                // 4. Pinned repos
                Text(
                    text = "Pinned",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Spacer(modifier = Modifier.height(8.dp))
                PinnedReposRow(repos = profile.pinnedRepos)
            }
        }
    }
}

// ─── Preview ─────────────────────────────────────────────────────────────────

@Preview(showBackground = true, name = "GitHub Profile — Portrait")
@Composable
private fun GitHubProfilePortraitPreview() {
    ComposeTrainingTheme {
        GitHubProfileCard(profile = sampleProfile)
    }
}

@Preview(
    showBackground = true,
    name = "GitHub Profile — Large Font",
    fontScale = 1.5f,
)
@Composable
private fun GitHubProfileLargeFontPreview() {
    ComposeTrainingTheme {
        GitHubProfileCard(profile = sampleProfile)
    }
}

@Preview(
    showBackground = true,
    name = "GitHub Profile — Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun GitHubProfileDarkPreview() {
    ComposeTrainingTheme {
        GitHubProfileCard(profile = sampleProfile)
    }
}
