package com.example.kmptraining.migrate.session2.session2_5.component

import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.dp

// TODO: [Session 2] Bài tập 4 - Implement TagsLayout composable dùng Layout
@Composable
fun TagsLayout(
    tags: List<String>,
    onTagClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Layout(
        content = {
            tags.forEach { tag ->
                // Render từng tag thành AssistChip hoặc SuggestionChip
                SuggestionChip(onClick = { onTagClick(tag) }, label = { Text(tag) })
            }
        },
        modifier = modifier,
    ) { measurables, constraints ->
        val placeables = measurables.map { it.measure(constraints.copy(minWidth = 0)) }
        val gap = 8.dp.roundToPx()

        var xPosition = 0
        var yPosition = 0
        var currentRowHeight = 0
        var maxRowWidth = 0

        for (placeable in placeables) {
            if (xPosition + placeable.width > constraints.maxWidth && xPosition > 0) {
                maxRowWidth = maxOf(maxRowWidth, xPosition - gap)
                xPosition = 0
                yPosition += currentRowHeight + gap
                currentRowHeight = 0
            }
            currentRowHeight = maxOf(currentRowHeight, placeable.height)
            xPosition += placeable.width + gap
        }
        maxRowWidth = maxOf(maxRowWidth, xPosition - gap)

        val totalHeight = if (placeables.isEmpty()) 0 else yPosition + currentRowHeight

        val layoutWidth =
            if (constraints.hasBoundedWidth) {
                constraints.maxWidth
            } else {
                maxRowWidth
            }

        layout(width = layoutWidth, height = totalHeight) {
            var x = 0
            var y = 0
            var rowH = 0

            placeables.forEach { placeable ->
                if (x + placeable.width > layoutWidth && x > 0) {
                    x = 0
                    y += rowH + gap
                    rowH = 0
                }

                placeable.placeRelative(x, y)

                rowH = maxOf(rowH, placeable.height)
                x += placeable.width + gap
            }
        }
    }
}
