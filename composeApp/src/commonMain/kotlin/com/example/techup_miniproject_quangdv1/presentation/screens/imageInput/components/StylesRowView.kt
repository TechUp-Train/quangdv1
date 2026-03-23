package com.example.techup_miniproject_quangdv1.presentation.screens.imageInput.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.example.techup_miniproject_quangdv1.core.theme.BrandMagenta
import com.example.techup_miniproject_quangdv1.domain.model.StyleItemModel

@Composable
fun StylesRowView(
    styles: List<StyleItemModel?>,
    selectedStyleId: String?,
    onStyleSelected: (String) -> Unit
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        items(styles) { style ->

            val isSelected = style?.styleId == selectedStyleId

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable {
                    style?.styleId?.let(onStyleSelected)
                }
            ) {

                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .border(
                            width = if (isSelected) 2.dp else 0.dp,
                            color = BrandMagenta,
                            shape = RoundedCornerShape(16.dp)
                        )
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(style?.imageUrl),
                        contentDescription = null,
                        modifier = Modifier.matchParentSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = style?.styleName ?: "",
                    color = if (isSelected) BrandMagenta else Color.Gray,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}