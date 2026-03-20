package com.example.kmptraining.kmp_session4.presentation.screens.explore.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.kmptraining.kmp_session4.domain.model.RepoModel

@Composable
fun SearchRepoItem(
    repo: RepoModel,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent // Items are on dark gradient background
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Avatar/Icon
            AsyncImage(
                model = repo.owner.avatarUrl,
                contentDescription = "Owner Avatar",
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White.copy(alpha = 0.1f))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = repo.fullName,
                        color = Color(0xFF4DA3FF),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    
                    Text(
                        text = "★ ${formatNumber(repo.stars)}",
                        color = Color(0xFF8B949E),
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = repo.description ?: "",
                    color = Color(0xFF8B949E),
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    LanguageDot(repo.language)

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = repo.language ?: "Unknown",
                        color = Color(0xFF8B949E),
                        fontSize = 12.sp
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = "Updated Oct 24", // Static for now as repo model lacks specific last_updated string
                        color = Color(0xFF8B949E),
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun LanguageDot(language: String?) {
    val color = when (language?.lowercase()) {
        "rust" -> Color(0xFFDEA584)
        "javascript" -> Color(0xFFFFD54F)
        "typescript" -> Color(0xFF42A5F5)
        "kotlin" -> Color(0xFFA97BFF)
        "java" -> Color(0xFFB07219)
        "css" -> Color(0xFF29B6F6)
        "html" -> Color(0xFFE34C26)
        else -> Color.Gray
    }

    Box(
        modifier = Modifier
            .size(10.dp)
            .clip(CircleShape)
            .background(color)
    )
}

private fun formatNumber(number: Int?): String {
    if (number == null) return "0"
    return if (number >= 1000) {
        val value = number / 1000.0
        val formatted = ((value * 10).toInt() / 10.0).toString()
        if (formatted.endsWith(".0")) "${formatted.substringBefore(".")}k" else "${formatted}k"
    } else {
        number.toString()
    }
}
