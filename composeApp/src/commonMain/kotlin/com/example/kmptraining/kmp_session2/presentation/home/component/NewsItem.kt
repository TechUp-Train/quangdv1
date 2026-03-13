package com.example.kmptraining.kmp_session2.presentation.home.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.kmptraining.kmp_session2.data.News

@Composable
fun NewsItem(news: News, onNewsClick: (News) -> Unit) {
    Card(
        modifier = Modifier.padding(vertical = 10.dp)
            .fillMaxWidth()
            .height(100.dp),
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
        Row {
            AsyncImage(
                model = news.imageUrl,
                contentDescription = "News thumb",
                modifier = Modifier.clip(RoundedCornerShape(5.dp))
                    .width(150.dp)
                    .fillMaxHeight()
            )
            Spacer(Modifier.width(10.dp))
            Column(
                modifier = Modifier
                    .padding(10.dp).weight(1f)
                    .fillMaxHeight()
            ) {
                Text(
                    news.title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 20.sp
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    news.author,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Clip
                )
            }
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