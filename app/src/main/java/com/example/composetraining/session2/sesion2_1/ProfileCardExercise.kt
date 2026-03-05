package com.example.composetraining.session2.sesion2_1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apero.composetraining.common.ProfileCard
import com.example.composetraining.session2.sesion2_1.component.ProfileCard
import com.example.composetraining.ui.theme.ComposeTrainingTheme

/**
 * ⭐ BÀI TẬP 1: Profile Card (Easy — 30 phút)
 *
 * Yêu cầu:
 * - Avatar: Box + CircleShape, hiển thị chữ cái đầu tên (KHÔNG dùng Image thật)
 * - Tên (16sp bold) + job title (14sp gray) bên dưới avatar
 * - Stats row: 3 cột (Posts | Followers | Following) với số bold + label nhỏ
 *   → Dùng IntrinsicSize.Min để 3 cột bằng chiều cao nhau
 *   → VerticalDivider giữa các cột
 * - Follow button full-width ở dưới cùng
 * - Spacer(Modifier.weight(1f)) để đẩy Follow button xuống
 *
 * Tiêu chí:
 * - Modifier.height(IntrinsicSize.Min) trên stats Row
 * - VerticalDivider() giữa các stat column
 * - Follow button luôn ở bottom dù card cao thấp khác nhau
 *
 * Gợi ý:
 * - Avatar initials: profile.name.first().toString()
 * - Stat column: Column(horizontalAlignment = CenterHorizontally) { Text(count); Text(label) }
 */

data class UserProfile(
    val name: String,
    val jobTitle: String,
    val postsCount: Int,
    val followersCount: Int,
    val followingCount: Int
)

@Composable
fun ProfileCardScreen() {
    val profile = UserProfile(
        name = "Đoàn Việt Quang",
        jobTitle = "Android Developer tại Apero",
        postsCount = 128,
        followersCount = 1200,
        followingCount = 890
    )

    ProfileCard(
        profile = profile,
        onFollowClick = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun ProfileCardScreenPreview() {
    ComposeTrainingTheme { ProfileCardScreen() }
}
