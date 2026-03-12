package com.example.kmptraining.migrate.session3.session3_2.component

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kmptraining.migrate.common.bg_card
import com.example.kmptraining.migrate.common.bg_page
import com.example.kmptraining.migrate.session3.session3_2.CartItem
import com.example.kmptraining.migrate.session3.session3_2.ModifyAction
import com.example.kmptraining.migrate.ui.theme.ComposeTrainingTheme

@Composable
fun ProductItem(
    cartItem: CartItem,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
                .border(0.5.dp, Color.LightGray.copy(alpha = 0.5f), RoundedCornerShape(15.dp))
                .background(bg_card)
                .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = cartItem.name,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "$${cartItem.price}",
                style =
                    TextStyle(
                        color = Color.Gray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                    ),
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            QuantitySelector(modifyAction = ModifyAction.DECREASE, onModify = onDecrease)
            Text(
                text = cartItem.quantity.toString(),
                style =
                    TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                    ),
            )
            QuantitySelector(modifyAction = ModifyAction.INCREASE, onModify = onIncrease)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductItemPreview() {
    ComposeTrainingTheme {
        Box(
            modifier =
                Modifier.background(bg_page)
                    .padding(10.dp),
        ) {
            ProductItem(
                cartItem = CartItem(1, "iPhone 16 Pro", 999.0, 2),
                onIncrease = {},
                onDecrease = {},
            )
        }
    }
}
