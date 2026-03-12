package com.example.kmptraining.migrate.session5.session5_3.screens.productList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kmptraining.migrate.session5.session5_3.data.Product
import com.example.kmptraining.migrate.session5.session5_3.data.sampleProducts

@Composable
fun ProductListScreen(
    products: List<Product>,
    onProductClick: (Product) -> Unit,
    cartCount: Int
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFDBFFF7))
            .padding(horizontal = 2.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items(products.size) { index ->
            ProductItem(
                product = products[index],
                onClick = { onProductClick(it) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductListPreview() {
    ProductListScreen(
        products = sampleProducts,
        onProductClick = {},
        cartCount = 0
    )
}