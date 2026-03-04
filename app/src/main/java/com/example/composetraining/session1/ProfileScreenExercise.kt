package com.example.composetraining.session1

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetraining.R
import com.example.composetraining.ui.theme.ComposeTrainingTheme
import java.time.format.TextStyle

/**
 * ⭐⭐⭐ BÀI TẬP 3: Mini Profile Screen (Challenge)
 *
 * Yêu cầu:
 * - Header: Avatar + Name + "Edit Profile" button
 * - Stats Row: 3 cột (Posts | Followers | Following) dùng Row + weight
 * - Bio section: Text nhiều dòng
 * - Action buttons: "Message" + "Follow" ngang hàng
 * - Bonus: Thêm Spacer hợp lý, đúng alignment
 */

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // === HEADER ===
        // TODO: [Session 1] Bài tập 3 - Tạo header với Avatar (80dp, CircleShape) + Column(Name, "Edit Profile" button)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            )

            Column {
                Text(
                    stringResource(R.string.user_name), style = androidx.compose.ui.text.TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                )
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray.copy(alpha = 0.1f), contentColor = Color.Black
                    ),
                    border = BorderStroke(
                        width = 1.dp, color = Color.Gray.copy(alpha = 0.2f)
                    ),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 5.dp),
                    modifier = Modifier.defaultMinSize(minHeight = 1.dp, minWidth = 1.dp),
                    onClick = {},
                ) {
                    Text(stringResource(R.string.edit_profile))
                }
            }
        }

        HorizontalDivider(
            thickness = 1.dp,
            color = Color.Gray.copy(alpha = 0.2f),
            modifier = Modifier.padding(vertical = 10.dp)
        )

        // === STATS ROW ===
        // TODO: [Session 1] Bài tập 3 - Tạo Row với 3 cột: Posts (128) | Followers (1.2K) | Following (380)
        // Gợi ý: Mỗi cột là Column(horizontalAlignment = CenterHorizontally, modifier = Modifier.weight(1f))
        // Số to + bold, label nhỏ + màu nhạt
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    100.toString(), style = androidx.compose.ui.text.TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 16.sp,
                    )
                )
                Text(
                    stringResource(R.string.posts), style = androidx.compose.ui.text.TextStyle(
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray.copy(alpha = 0.6f),
                        fontSize = 14.sp,
                    )
                )
            }

            VerticalDivider(
                modifier = Modifier.padding(vertical = 5.dp),
                thickness = 1.dp,
                color = Color.Gray.copy(alpha = 0.2f)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    1000.toString(), style = androidx.compose.ui.text.TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 16.sp,
                    )
                )
                Text(
                    stringResource(R.string.followers), style = androidx.compose.ui.text.TextStyle(
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray.copy(alpha = 0.6f),
                        fontSize = 14.sp,
                    )
                )
            }

            VerticalDivider(
                modifier = Modifier.padding(vertical = 5.dp),
                thickness = 1.dp,
                color = Color.Gray.copy(alpha = 0.2f)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    200.toString(), style = androidx.compose.ui.text.TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 16.sp,
                    )
                )
                Text(
                    stringResource(R.string.following), style = androidx.compose.ui.text.TextStyle(
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray.copy(alpha = 0.6f),
                        fontSize = 14.sp,
                    )
                )
            }

        }

        HorizontalDivider(
            thickness = 1.dp,
            color = Color.Gray.copy(alpha = 0.2f),
            modifier = Modifier.padding(vertical = 10.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // === BIO ===
        // TODO: [Session 1] Bài tập 3 - Thêm Text bio nhiều dòng
        // Ví dụ: "Android Developer 📱\nLover of clean code ✨\nHà Nội, Việt Nam 🇻🇳"

        Text(
            stringResource(R.string.bio),
            style = androidx.compose.ui.text.TextStyle(
                fontWeight = FontWeight.Normal,
                lineHeight = 20.sp,
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        // === ACTION BUTTONS ===
        // TODO: [Session 1] Bài tập 3 - Tạo Row chứa 2 button ngang hàng: "Message" (Outlined) + "Follow" (Filled)
        // Gợi ý: Dùng Modifier.weight(1f) cho mỗi button, spacedBy(8.dp) cho Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {}, modifier = Modifier
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.Black),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(width = 1.dp, color = Color.Gray.copy(alpha = 0.2f))
            ) {
                Text(stringResource(R.string.message))
            }

            Button(
                onClick = {}, modifier = Modifier
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue, contentColor = Color.White),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(width = 1.dp, color = Color.Gray.copy(alpha = 0.2f))
            ) {
                Text(stringResource(R.string.follow))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    ComposeTrainingTheme() { ProfileScreen() }
}
