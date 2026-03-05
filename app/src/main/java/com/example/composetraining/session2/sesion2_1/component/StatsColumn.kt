package com.example.composetraining.session2.sesion2_1.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

// TODO: [Session 2] Bài tập 2 - Implement StatColumn composable (private, stateless)
// Params: label: String, count: Int
// Layout: Column(CenterHorizontally) { Text(count bold 18sp); Text(label gray 12sp) }
@Composable
fun StatsColumn(label: String, count: Int, modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = count.toString(),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Text(
            text = label,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun StatsColumnPreview() {
    StatsColumn(label = "Posts", count = 128, modifier = Modifier)
}