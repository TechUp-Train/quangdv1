package com.example.kmptraining.kmp_session4.presentation.screens.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.kmptraining.kmp_session4.domain.model.UserModel

@Composable
fun ProfileHeader(user: UserModel) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.size(100.dp)) {
            AsyncImage(
                model = user.avatarUrl,
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape)
                    .background(Color.DarkGray)
                    .align(Alignment.Center)
            )
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF2EA043))
                    .border(2.dp, Color(0xFF0D1117), CircleShape)
                    .align(Alignment.BottomEnd)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = user.name ?: "",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "@${user.login}",
            color = Color(0xFF4DA3FF),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = user.bio ?: "",
            color = Color(0xFF8B949E),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Stats Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StatCard(value = user.publicRepos.toString(), label = "REPOS", modifier = Modifier.weight(1f))
            StatCard(value = formatNumber(user.followers), label = "FOLLOWERS", modifier = Modifier.weight(1f))
            StatCard(value = formatNumber(user.following), label = "FOLLOWING", modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun StatCard(value: String, label: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.height(72.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF161B22)
        ),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF30363D))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = value,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = label,
                color = Color(0xFF8B949E),
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

private fun formatNumber(number: Int): String {
    return if (number >= 1000) {
        val value = number / 1000.0
        val formatted = ((value * 10).toInt() / 10.0).toString()
        if (formatted.endsWith(".0")) "${formatted.substringBefore(".")}k" else "${formatted}k"
    } else {
        number.toString()
    }
}
