package com.example.composetraining.session1.session1_6.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetraining.session1.session1_6.data.PinnedRepo
import com.example.composetraining.session1.session1_6.data.sampleProfile

/**
 * Pinned repos — EQUAL HEIGHT với IntrinsicSize.Max
 *
 * TODO: [Nâng cao] Thêm graphicsLayer scale effect khi pressed
 *
 * Cách implement hover/press effect với graphicsLayer:
 * var isPressed by remember { mutableStateOf(false) }
 * PinnedRepoCard(
 *     modifier = Modifier
 *         .clickable { ... }
 *         .graphicsLayer {
 *             scaleX = if (isPressed) 0.97f else 1f
 *             scaleY = if (isPressed) 0.97f else 1f
 *         }
 * )
 * Note: State cần Buổi 3 — hôm nay mock với alpha thay đổi tĩnh
 */
@Composable
fun PinnedReposRow(
    repos: List<PinnedRepo>,
    modifier: Modifier = Modifier,
) {
    // EQUAL HEIGHT — IntrinsicSize.Max
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max),
        // ← Equal height magic
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        repos.forEach { repo ->
            PinnedRepoCard(
                repo = repo,
                modifier =
                    Modifier
                        .weight(1f)
                        .fillMaxHeight(), // ← Phối hợp với IntrinsicSize.Max
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PinnedReposRowPreview() {
    PinnedReposRow(
        repos = sampleProfile.pinnedRepos,
    )
}
