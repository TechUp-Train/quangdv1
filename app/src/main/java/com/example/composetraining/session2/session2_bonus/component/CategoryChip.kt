package com.example.composetraining.session2.session2_bonus.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetraining.common.h
import com.example.composetraining.common.w

@Composable
fun FilterChip(category: String, selected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (selected) Color.White else Color.Black.copy(alpha = 0.6f))
            .clickable { onClick() }
            .padding(horizontal = 3.w, vertical = 1.h)
    ) {
        Text(
            category,
            style = TextStyle(
                color = if (selected) Color.Black else Color.White,
                fontSize = 16.sp,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FilterChipPreview() {
    FilterChip("All", false) {}
}