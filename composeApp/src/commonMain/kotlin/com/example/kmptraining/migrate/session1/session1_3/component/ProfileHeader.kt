package com.example.kmptraining.migrate.session1.session1_3.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kmptraining.composeapp.generated.resources.Res
import kmptraining.composeapp.generated.resources.edit_profile
import kmptraining.composeapp.generated.resources.user_name
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProfileHeader() {
    // === HEADER ===
    // TODO: [Session 1] Bài tập 3 - Tạo header với Avatar (80dp, CircleShape) + Column(Name, "Edit Profile" button)
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = null,
            modifier =
                Modifier
                    .size(80.dp)
                    .clip(CircleShape),
        )

        Column {
            Text(
                stringResource(Res.string.user_name),
                style =
                    TextStyle(
                        fontWeight = FontWeight.Bold,
                    ),
            )
            Button(
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = Color.Gray.copy(alpha = 0.1f),
                        contentColor = Color.Black,
                    ),
                border =
                    BorderStroke(
                        width = 1.dp,
                        color = Color.Gray.copy(alpha = 0.2f),
                    ),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 5.dp),
                modifier = Modifier.defaultMinSize(minHeight = 1.dp, minWidth = 1.dp),
                onClick = {},
            ) {
                Text(stringResource(Res.string.edit_profile))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileHeaderPreview() {
    ProfileHeader()
}
