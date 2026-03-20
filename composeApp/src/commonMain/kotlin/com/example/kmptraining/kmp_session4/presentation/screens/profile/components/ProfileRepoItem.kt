package com.example.kmptraining.kmp_session4.presentation.screens.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.example.kmptraining.kmp_session4.domain.model.RepoModel

@Composable
fun ProfileRepoItem(
    repo: RepoModel,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF161B22) // Match the dark theme background for profile card
        ),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF30363D))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Repo Icon (using simple box with text for now as icon placeholder)
                Text(text = "📚", fontSize = 16.sp)
                
                Spacer(modifier = Modifier.width(8.dp))
                
                Text(
                    text = repo.name,
                    color = Color(0xFF4DA3FF),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f, fill = false)
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Public Badge
                Box(
                    modifier = Modifier
                        .border(1.dp, Color(0xFF30363D), RoundedCornerShape(50))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "Public",
                        color = Color(0xFF8B949E),
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = repo.description ?: "",
                color = Color(0xFF8B949E),
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(16.dp))

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
                    text = "★ ${formatNumber(repo.stars)}",
                    color = Color(0xFF8B949E),
                    fontSize = 12.sp
                )
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
