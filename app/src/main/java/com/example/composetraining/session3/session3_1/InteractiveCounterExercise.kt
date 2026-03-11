package com.example.composetraining.session3.session3_1

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetraining.ui.theme.ComposeTrainingTheme

/**
 * ⭐ BÀI TẬP 1: Interactive Counter (Easy)
 *
 * Yêu cầu:
 * - Text hiển thị count (fontSize 48sp)
 * - Row: Button "-" | Button "+" | Button "Reset"
 * - Count không được < 0
 * - Dùng remember + mutableStateOf
 * - Rotate thiết bị → count vẫn giữ (rememberSaveable)
 */

@Composable
fun InteractiveCounterScreen() {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Interactive Counter", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(24.dp))

        // TODO: [Session 3] Bài tập 1 - Tạo biến count dùng rememberSaveable
        var count by rememberSaveable { mutableIntStateOf(0) }

        // TODO: [Session 3] Bài tập 1 - Hiển thị count với fontSize 48sp
        Text(
            text = count.toString(),
            style =
                TextStyle(
                    fontSize = 48.sp,
                ),
        )

        Spacer(modifier = Modifier.height(16.dp))

        // TODO: [Session 3] Bài tập 1 - Tạo Row chứa 3 buttons: "-", "+", "Reset"
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Button(onClick = { if (count > 0) count-- }) {
                Text(text = "-")
            }
            Button(onClick = { count++ }) {
                Text(text = "+")
            }
            Button(onClick = { count = 0 }) {
                Text(text = "Reset")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun InteractiveCounterScreenPreview() {
    ComposeTrainingTheme { InteractiveCounterScreen() }
}
