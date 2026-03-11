package com.example.composetraining.session1.session1_1

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetraining.ui.theme.ComposeTrainingTheme

/**
 * ⭐ BÀI TẬP 1: Greeting Card (Easy)
 *
 * Yêu cầu:
 * - Tạo 1 Card chứa: Icon + Text "Hello, [tên mình]!" + Button "Say Hi"
 * - Click button → đổi text thành "Hi back!"
 * - Dùng Column layout
 * - Modifier: padding 16dp, fillMaxWidth
 */

@Composable
fun GreetingCard() {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
    ) {
        var text by remember { mutableStateOf("Hello, Quang!") }

        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
        ) {
            // TODO: [Session 1] Bài tập 1 - Thêm Icon từ Material Icons (ví dụ: Icons.Default.Favorite)
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "avatar",
                modifier = Modifier.size(60.dp),
            )

            // TODO: [Session 1] Bài tập 1 - Thêm Text "Hello, [tên bạn]!" với fontSize 24sp, FontWeight.Bold
            // Gợi ý: Dùng remember + mutableStateOf để lưu trạng thái text
            Text(
                text = text,
                style =
                    TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    ),
            )

            // TODO: [Session 1] Bài tập 1 - Thêm Button "Say Hi"
            // Khi click → đổi text thành "Hi back!"
            // Gợi ý: var greeting by remember { mutableStateOf("Hello, ...!") }
            Button(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                onClick = {
                    text = "Hi back!"
                },
            ) {
                Text(text = "Say Hi")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingCardPreview() {
    ComposeTrainingTheme { GreetingCard() }
}
