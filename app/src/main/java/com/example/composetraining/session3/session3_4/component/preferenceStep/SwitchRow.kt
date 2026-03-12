package com.example.composetraining.session3.session3_4.component.preferenceStep

import android.widget.Switch
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetraining.common.w
import com.example.composetraining.ui.theme.ComposeTrainingTheme

class RowIcon(
    val icon: ImageVector,
    val color: Color,
)

@Composable
fun SwitchRow(
    label: String,
    description: String,
    icon: RowIcon,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    // - Row(fillMaxWidth, SpaceBetween, CenterVertically)
    // - Text label (bodyLarge) + Switch(checked, onCheckedChange)
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .background(Color.White)
                .clip(RoundedCornerShape(10.dp))
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                ).padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RowIcon(rowIcon = icon)
        Spacer(Modifier.width(2.w))
        Column(
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = label,
                style =
                    MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 18.sp,
                    ),
            )
            Text(
                text = description,
                style =
                    MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Gray,
                        fontSize = 14.sp,
                    ),
            )
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.padding(end = 10.dp),
            colors =
                SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    uncheckedThumbColor = Color.White,
                    checkedTrackColor = Color.Blue.copy(alpha = 0.6f),
                    uncheckedTrackColor = Color(0xFFE0DFDC),
                    checkedBorderColor = Color.Transparent,
                    uncheckedBorderColor = Color.Transparent,
                ),
        )
    }
}

@Composable
fun RowIcon(
    rowIcon: RowIcon,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            Modifier
                .clip(RoundedCornerShape(5.dp))
                .background(rowIcon.color.copy(alpha = 0.1f))
                .padding(5.dp),
    ) {
        Icon(
            imageVector = rowIcon.icon,
            contentDescription = null,
            tint = rowIcon.color.copy(alpha = 0.4f),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun SwitchRowPreview() {
    ComposeTrainingTheme {
        SwitchRow(
            label = "Notifications",
            description = "Enable or disable notifications",
            checked = true,
            icon =
                RowIcon(
                    icon = Icons.Default.Email,
                    color = Color.Blue,
                ),
            onCheckedChange = {},
        )
    }
}
