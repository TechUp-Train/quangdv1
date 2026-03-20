package com.example.kmptraining.kmp_session4.presentation.screens.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kmptraining.kmp_session4.domain.model.RepoModel

@Composable
fun RepoItem(
    repo: RepoModel,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF162A44)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "${repo.owner.login} / ${repo.name}",
                    color = Color(0xFF4DA3FF),
                    fontWeight = FontWeight.Bold
                )

                OutlinedButton(
                    onClick = {},
                    border = BorderStroke(1.dp, Color.Gray),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier.height(32.dp)
                ) {
                    Text("Star", color = Color.White, fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = repo.description ?: "No description provided",
                color = Color.LightGray,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                LanguageDot(repo.language)

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = repo.language ?: "Unknown",
                    color = Color.LightGray,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "★ ${repo.stars}",
                    color = Color.LightGray,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "⑂ ${repo.forksCount}",
                    color = Color.LightGray,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
private fun LanguageDot(language: String?) {
    val color = when (language?.lowercase()) {
        "javascript" -> Color(0xFFFFD54F)
        "typescript" -> Color(0xFF42A5F5)
        "css" -> Color(0xFF29B6F6)
        else -> Color.Gray
    }

    Box(
        modifier = Modifier
            .size(8.dp)
            .clip(CircleShape)
            .background(color)
    )
}