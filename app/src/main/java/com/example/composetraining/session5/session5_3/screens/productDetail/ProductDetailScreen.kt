package com.example.composetraining.session5.session5_3.screens.productDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetraining.common.h
import com.example.composetraining.common.w
import com.example.composetraining.session5.session5_3.data.Product
import com.example.composetraining.session5.session5_3.data.sampleProducts

@Composable
fun ProductDetailScreen(
    product: Product,
    onAddToCart: (Product) -> Unit,
    onViewCart: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 2.w, vertical = 2.h)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(25.h)
                .clip(RoundedCornerShape(10.dp))
                .background(color = product.color)
        )
        Text(
            product.name,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(vertical = 2.h)
        )
        Text(
            "$${product.price}",
            style = MaterialTheme.typography.headlineSmall.copy(
                color = product.color.copy(alpha = 0.8f),
                fontWeight = FontWeight.Bold
            ),
        )
        Text(
            product.description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 2.h)
        )
        Spacer(Modifier.weight(1f))
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            border = null,
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color(0xFF3FB046),
                contentColor = Color.White,
            ),
            shape = RoundedCornerShape(5.dp),
            onClick = { onAddToCart(product) },
        ) {
            Text("Add to Cart")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductDetailPreview() {
    ProductDetailScreen(
        product = sampleProducts.last(),
        onAddToCart = {},
        onViewCart = {},
        onBack = {}
    )
}