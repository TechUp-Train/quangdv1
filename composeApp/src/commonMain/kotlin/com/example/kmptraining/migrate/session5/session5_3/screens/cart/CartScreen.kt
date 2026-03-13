package com.example.kmptraining.migrate.session5.session5_3.screens.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kmptraining.migrate.session5.session5_3.data.Product
import com.example.kmptraining.migrate.session5.session5_3.data.sampleProducts

@Composable
fun CartScreen(
    items: List<Product>,
    onBack: () -> Unit,
    onRemoveItem: (Product) -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(items) { product ->
                    CartItem(product = product, onRemove = { onRemoveItem(product) })
                }
            }

            CartSummary(items = items)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CartPreview() {
    CartScreen(
        items = sampleProducts,
        onBack = { }
    )
}