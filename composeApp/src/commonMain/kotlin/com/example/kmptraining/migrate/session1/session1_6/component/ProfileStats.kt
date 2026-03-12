package com.example.kmptraining.migrate.session1.session1_6.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kmptraining.migrate.session1.session1_6.data.sampleProfile

@Composable
fun ProfileStats(
    repoCount: Int,
    starCount: Int,
    followerCount: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        StatItem(
            icon = Icons.Outlined.MailOutline,
            count = repoCount,
            label = "repos",
        )

        VerticalDivider(modifier = Modifier.height(32.dp))

        StatItem(
            icon = Icons.Outlined.Star,
            count = starCount,
            label = "stars",
        )

        VerticalDivider(modifier = Modifier.height(32.dp))

        StatItem(
            icon = Icons.Outlined.Person,
            count = followerCount,
            label = "followers",
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileStatsPreview() {
    ProfileStats(
        repoCount = sampleProfile.repoCount,
        starCount = sampleProfile.starCount,
        followerCount = sampleProfile.followerCount,
    )
}
