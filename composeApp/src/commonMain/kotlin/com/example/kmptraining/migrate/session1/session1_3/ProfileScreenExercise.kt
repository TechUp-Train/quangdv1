package com.example.kmptraining.migrate.session1.session1_3

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kmptraining.migrate.session1.session1_3.component.ProfileButtons
import com.example.kmptraining.migrate.session1.session1_3.component.ProfileHeader
import com.example.kmptraining.migrate.session1.session1_3.component.StatsRow
import com.example.kmptraining.migrate.ui.theme.ComposeTrainingTheme
import kmptraining.composeapp.generated.resources.Res
import kmptraining.composeapp.generated.resources.bio
import org.jetbrains.compose.resources.stringResource

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
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
    ) {
        ProfileHeader()

        HorizontalDivider(
            thickness = 1.dp,
            color = Color.Gray.copy(alpha = 0.2f),
            modifier = Modifier.padding(vertical = 10.dp),
        )

        StatsRow()

        HorizontalDivider(
            thickness = 1.dp,
            color = Color.Gray.copy(alpha = 0.2f),
            modifier = Modifier.padding(vertical = 10.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        // === BIO ===
        // TODO: [Session 1] Bài tập 3 - Thêm Text bio nhiều dòng
        // Ví dụ: "Android Developer 📱\nLover of clean code ✨\nHà Nội, Việt Nam 🇻🇳"
        Text(
            stringResource(Res.string.bio),
            style =
                TextStyle(
                    fontWeight = FontWeight.Normal,
                    lineHeight = 20.sp,
                ),
        )

        Spacer(modifier = Modifier.height(24.dp))

        ProfileButtons()
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    ComposeTrainingTheme { ProfileScreen() }
}
