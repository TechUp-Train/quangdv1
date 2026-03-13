package com.example.kmptraining.kmp_session2.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.kmptraining.kmp_session2.data.News

@Composable
fun NewsItem(news: News, onNewsClick: (News) -> Unit) {
    Row(
        modifier = Modifier
            .clickable { onNewsClick(news) }
            .padding(horizontal = 2.dp, vertical = 10.dp)
            .background(Color.Transparent)
            .clip(RoundedCornerShape(10.dp))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.tertiary
            )
            .padding(5.dp)
    ) {
        AsyncImage(
            model = news.imageUrl,
            contentDescription = "News thumb",
            modifier = Modifier.clip(RoundedCornerShape(5.dp))
        )
        Spacer(Modifier.width(2.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                news.title,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                news.content,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Light
                ),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsItemPreview() {
    NewsItem(
        news = News(
            id = 1,
            title = "Test News",
            content = "asduigahsuioyvb8wq7iqeunqw7iehfncdxiwefhnciewnaafhwuxfhxnxxnwxh9fhrmrwe8fg90erpjwc,pftowercf89-wejmrf9",
            imageUrl = "",
            author = ""
        )
    ) {}
}