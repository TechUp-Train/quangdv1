package com.example.composetraining.session2.session2_1.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetraining.R
import com.example.composetraining.common.h
import com.example.composetraining.common.w
import com.example.composetraining.session2.session2_1.UserProfile

// TODO: [Session 2] Bài tập 1 - Implement ProfileCard composable
// Params: profile: UserProfile, onFollowClick: () -> Unit, modifier: Modifier = Modifier
// Layout gợi ý:
//   Card {
//     Column(horizontalAlignment = CenterHorizontally) {
//       Box(CircleShape, background = primary) { Text(initials) }  ← Avatar
//       Text(name, bold 16sp)
//       Text(jobTitle, gray 14sp)
//       Spacer(weight 1f)
//       Row(Modifier.height(IntrinsicSize.Min)) {               ← Stats row
//         StatColumn("Posts", postsCount)
//         VerticalDivider()
//         StatColumn("Followers", followersCount)
//         VerticalDivider()
//         StatColumn("Following", followingCount)
//       }
//       Spacer(weight 1f)
//       Button(onFollowClick) { Text("Follow") }                ← Follow button
//     }
//   }
@Composable
fun ProfileCard(profile: UserProfile, onFollowClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 2.h),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = profile.name.first().toString(),
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(Modifier.width(5.w))
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        profile.name, style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Text(
                        profile.jobTitle, style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Gray
                        )
                    )
                }
            }

            HorizontalDivider(
                color = MaterialTheme.colorScheme.outline,
                thickness = 1.dp
            )

            Row(
                Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .padding(vertical = 1.h),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                StatsColumn(
                    stringResource(R.string.posts),
                    profile.postsCount,
                    modifier = Modifier.weight(1f)
                )
                VerticalDivider(
                    color = MaterialTheme.colorScheme.outline,
                    thickness = 0.5.dp
                )
                StatsColumn(
                    stringResource(R.string.followers),
                    profile.followersCount,
                    modifier = Modifier.weight(1f)
                )
                VerticalDivider(
                    color = MaterialTheme.colorScheme.outline,
                    thickness = 0.5.dp
                )
                StatsColumn(
                    stringResource(R.string.following),
                    profile.followingCount,
                    modifier = Modifier.weight(1f)
                )
            }

            HorizontalDivider(
                color = MaterialTheme.colorScheme.outline,
                thickness = 1.dp
            )

            Spacer(Modifier.weight(1f))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.h),
                onClick = onFollowClick,
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    stringResource(R.string.follow),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileCardPreview() {
    ProfileCard(
        profile = UserProfile(
            name = "Đoàn Việt Quang",
            jobTitle = "Android Developer tại Apero",
            postsCount = 128,
            followersCount = 1200,
            followingCount = 890
        ),
        onFollowClick = {}
    )
}