package com.example.kmptraining.migrate.session1.session1_6.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Language chips — Wrap layout
 *
 * TODO: [Nâng cao] Implement FlowRow để chips wrap tự động
 *
 * Option 1 (Easy): Dùng accompanist FlowRow
 * dependencies { implementation "com.google.accompanist:accompanist-flowlayout:0.34.0" }
 *
 * Option 2 (Hard): Tự implement với Layout composable
 * @Composable
 * fun FlowRow(content: @Composable () -> Unit) {
 *     Layout(content = content) { measurables, constraints ->
 *         // Measure và place từng item, wrap khi hết row width
 *     }
 * }
 *
 * Hôm nay: Dùng Row wrap = nếu nhiều lang thì scroll hoặc chia 2 Row
 */
@Composable
fun LanguageChipsSection(
    languages: List<String>,
    modifier: Modifier = Modifier,
) {
    // TODO: [Nâng cao] Thay bằng FlowRow để auto-wrap
    // Tạm thời: chia 2 row
    val firstRow = languages.take(3)
    val secondRow = languages.drop(3)

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            firstRow.forEach { lang -> LanguageChip(language = lang) }
        }
        if (secondRow.isNotEmpty()) {
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                secondRow.forEach { lang -> LanguageChip(language = lang) }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LanguageChipsSectionPreview() {
    LanguageChipsSection(
        listOf("Kotlin", "Dart"),
    )
}
