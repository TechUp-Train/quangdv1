package com.example.composetraining.session1.session1_4.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PostActionBar(
    likeCount: Int,
    commentCount: Int,
    retweetCount: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        // TODO: [Nâng cao] Tạo ActionItem composable reusable thay vì lặp code
        // Gợi ý: ActionItem(icon, count, onClick, modifier)

        ActionItem(
            icon = Icons.Outlined.FavoriteBorder,
            count = likeCount,
            contentDescription = "Like",
        )
        ActionItem(
            icon = Icons.Outlined.Email,
            count = commentCount,
            contentDescription = "Comment",
        )
        ActionItem(
            icon = Icons.Outlined.Refresh,
            count = retweetCount,
            contentDescription = "Retweet",
        )
        ActionItem(
            icon = Icons.Outlined.Share,
            count = null,
            contentDescription = "Share",
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PostActionBarPreview() {
    PostActionBar(
        likeCount = 100,
        commentCount = 20,
        retweetCount = 70,
    )
}
