package com.example.composetraining.session1.session1_3.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetraining.R

@Composable
fun ProfileButtons() {
    // === ACTION BUTTONS ===
    // TODO: [Session 1] Bài tập 3 - Tạo Row chứa 2 button ngang hàng: "Message" (Outlined) + "Follow" (Filled)
    // Gợi ý: Dùng Modifier.weight(1f) cho mỗi button, spacedBy(8.dp) cho Row
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Button(
            onClick = {},
            modifier =
                Modifier
                    .weight(1f),
            colors =
                ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black,
                ),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(width = 1.dp, color = Color.Gray.copy(alpha = 0.2f)),
        ) {
            Text(stringResource(R.string.message))
        }

        Button(
            onClick = {},
            modifier =
                Modifier
                    .weight(1f),
            colors =
                ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White,
                ),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(width = 1.dp, color = Color.Gray.copy(alpha = 0.2f)),
        ) {
            Text(stringResource(R.string.follow))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileButtonsPreview() {
    ProfileButtons()
}
