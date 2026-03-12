package com.example.kmptraining.migrate.session4.session4_5.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.kmptraining.migrate.session4.session4_5.data.Photo
import com.example.kmptraining.migrate.session4.session4_5.data.samplePhotos
import com.example.kmptraining.migrate.ui.theme.ComposeTrainingTheme
import kotlin.math.abs

/**
 * Tính height deterministic từ title (không random)
 *
 * Tại sao không dùng Random?
 * → Compose có thể recompose bất kỳ lúc nào
 * → Random() mỗi lần recompose → height thay đổi → layout jump
 * → Dùng hashCode() đảm bảo cùng input = cùng output
 */
private fun calculateDeterministicHeight(title: String): Dp {
    val hash = abs(title.hashCode())
    val minHeight = 120
    val maxHeight = 280
    return (minHeight + hash % (maxHeight - minHeight)).dp
}

// ─── Staggered Grid ───────────────────────────────────────────────────────────
@Composable
fun StaggeredPhotoGrid(
    photos: List<Photo>,
    modifier: Modifier = Modifier,
) {
    // - LazyVerticalStaggeredGrid với:
    //   columns = StaggeredGridCells.Adaptive(150.dp)
    //   → Số cột tự tính để mỗi cột >= 150dp (responsive)
    //   → Hoặc: StaggeredGridCells.Fixed(2) để luôn 2 cột
    //   contentPadding = PaddingValues(horizontal=8.dp, vertical=8.dp)
    //   horizontalArrangement = spacedBy(8.dp)
    //   verticalItemSpacing = 8.dp
    //
    // - items(photos, key = { it.id }) { photo →
    //     PhotoCard(photo, height = calculateDeterministicHeight(photo.title))
    //   }
    //
    // GỢI Ý: Tại sao dùng Adaptive thay vì Fixed?
    // → Adaptive: responsive (tablet nhiều cột hơn phone)
    // → Fixed: predictable layout nhưng không responsive
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalItemSpacing = 8.dp
    ) {
        items(photos, key = { it.id }) { photo ->
            PhotoCard(photo = photo, height = calculateDeterministicHeight(photo.title))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StaggeredPhotoGridPreview() {
    ComposeTrainingTheme {
        StaggeredPhotoGrid(photos = samplePhotos)
    }
}