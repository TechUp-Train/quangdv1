package com.example.kmptraining.migrate.session1.session1_3.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kmptraining.composeapp.generated.resources.Res
import kmptraining.composeapp.generated.resources.followers
import kmptraining.composeapp.generated.resources.following
import kmptraining.composeapp.generated.resources.posts
import org.jetbrains.compose.resources.stringResource

@Composable
fun StatsRow() {
    // === STATS ROW ===
    // TODO: [Session 1] Bài tập 3 - Tạo Row với 3 cột: Posts (128) | Followers (1.2K) | Following (380)
    // Gợi ý: Mỗi cột là Column(horizontalAlignment = CenterHorizontally, modifier = Modifier.weight(1f))
    // Số to + bold, label nhỏ + màu nhạt
    Row(
        modifier =
            Modifier
                .height(IntrinsicSize.Min)
                .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f),
        ) {
            Text(
                100.toString(),
                style =
                    TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 16.sp,
                    ),
            )
            Text(
                stringResource(Res.string.posts),
                style =
                    TextStyle(
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray.copy(alpha = 0.6f),
                        fontSize = 14.sp,
                    ),
            )
        }

        VerticalDivider(
            modifier = Modifier.padding(vertical = 5.dp),
            thickness = 1.dp,
            color = Color.Gray.copy(alpha = 0.2f),
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f),
        ) {
            Text(
                1000.toString(),
                style =
                    TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 16.sp,
                    ),
            )
            Text(
                stringResource(Res.string.followers),
                style =
                    TextStyle(
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray.copy(alpha = 0.6f),
                        fontSize = 14.sp,
                    ),
            )
        }

        VerticalDivider(
            modifier = Modifier.padding(vertical = 5.dp),
            thickness = 1.dp,
            color = Color.Gray.copy(alpha = 0.2f),
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f),
        ) {
            Text(
                200.toString(),
                style =
                    TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 16.sp,
                    ),
            )
            Text(
                stringResource(Res.string.following),
                style =
                    TextStyle(
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray.copy(alpha = 0.6f),
                        fontSize = 14.sp,
                    ),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StatsRowPreview() {
    StatsRow()
}
