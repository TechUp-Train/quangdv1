package com.example.kmptraining.kmp_session2.presentation.home.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.kmptraining.kmp_session2.data.News
import com.example.kmptraining.kmp_session2.data.newsData

@Composable
fun NewsItem(news: News, itemHeight: Dp, onNewsClick: (News) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(itemHeight),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(
            color = Color.LightGray.copy(alpha = 0.4f),
            width = 1.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        onClick = { onNewsClick(news) }
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = news.imageUrl,
                contentDescription = "News thumb",
                modifier = Modifier.clip(RoundedCornerShape(20.dp))
                    .width(150.dp)
                    .fillMaxHeight()
            )
            Spacer(Modifier.width(15.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Text(
                    news.category,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    news.title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        news.author,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Light
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Clip
                    )
                    Text(
                        news.readDuration,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Light,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Clip
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsItemPreview() {
    NewsItem(
        news = newsData.first(),
        itemHeight = 150.dp
    ) {}
}