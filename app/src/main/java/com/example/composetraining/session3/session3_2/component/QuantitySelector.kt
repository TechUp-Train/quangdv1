package com.example.composetraining.session3.session3_2.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetraining.common.bg_muted
import com.example.composetraining.common.primary_green
import com.example.composetraining.session3.session3_2.ModifyAction

@Composable
fun QuantitySelector(modifyAction: ModifyAction, onModify: () -> Unit) {
    Button(
        onClick = onModify,
        modifier = Modifier.size(32.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (modifyAction == ModifyAction.INCREASE) primary_green else bg_muted,
            contentColor = if (modifyAction == ModifyAction.INCREASE) Color.White else Color.Black
        ),
        border = if (modifyAction == ModifyAction.DECREASE) BorderStroke(
            1.dp,
            Color.LightGray
        ) else null,
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = if (modifyAction == ModifyAction.DECREASE) "-" else "+",
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
        )
    }
}