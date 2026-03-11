package com.example.composetraining.session3.session3_4.component.preferenceStep

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetraining.common.w
import com.example.composetraining.ui.theme.ComposeTrainingTheme

@Composable
fun LanguageItem(
    language: String,
    isSelected: Boolean,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .clickable { onSelect(language) }
                .background(if (isSelected) Color.Blue.copy(alpha = 0.1f) else Color.Transparent),
        horizontalArrangement = Arrangement.spacedBy(2.w),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(
            selected = isSelected,
            onClick = { onSelect(language) },
            colors =
                RadioButtonColors(
                    selectedColor = Color.Blue.copy(alpha = 0.6f),
                    unselectedColor = Color.Gray.copy(alpha = 0.3f),
                    disabledSelectedColor = Color.Gray.copy(alpha = 0.3f),
                    disabledUnselectedColor = Color.Gray.copy(alpha = 0.3f),
                ),
        )
        Text(
            language,
            style =
                TextStyle(
                    color = if (isSelected) Color.Blue.copy(alpha = 0.6f) else Color.Black,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LanguageItemPreview() {
    ComposeTrainingTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                LanguageItem(
                    language = "English",
                    isSelected = true,
                    onSelect = {},
                )
                LanguageItem(
                    language = "Vietnamese",
                    isSelected = false,
                    onSelect = {},
                )
            }
        }
    }
}
