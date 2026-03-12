package com.example.composetraining.session3.session3_2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apero.composetraining.common.SampleData
import com.example.composetraining.common.bg_page
import com.example.composetraining.session3.session3_2.component.ProductItem
import com.example.composetraining.session3.session3_2.component.ShoppingCartBottomBar
import com.example.composetraining.session3.session3_2.component.ShoppingCartTopBar
import com.example.composetraining.ui.theme.ComposeTrainingTheme

/**
 * ⭐⭐ BÀI TẬP 2: Shopping Cart (Medium)
 *
 * Yêu cầu:
 * - LazyColumn: 5 products (Name + Price + Quantity selector [- count +])
 * - Bottom bar: "Total: $XXX" (derivedStateOf tính tổng)
 * - State hoisting: CartScreen(items, onQuantityChange)
 * - Total tự update khi thay đổi quantity
 * - Quantity không < 0
 * - Stateless product item component
 */

data class CartItem(
    val productId: Int,
    val name: String,
    val price: Double,
    val quantity: Int = 0,
)

enum class ModifyAction {
    INCREASE,
    DECREASE,
}

@Composable
fun ShoppingCartScreen() {
    val products = SampleData.products.take(5)

    // TODO: [Session 3] Bài tập 2 - Tạo state cho cart items
    val cartItems =
        remember {
            mutableStateListOf(*products.map { CartItem(it.id, it.name, it.price, 0) }.toTypedArray())
        }

    // TODO: [Session 3] Bài tập 2 - Tính total bằng derivedStateOf
    val total by remember { derivedStateOf { cartItems.sumOf { it.price * it.quantity } } }

    Scaffold(
        containerColor = bg_page,
        topBar = { ShoppingCartTopBar() },
        bottomBar = { ShoppingCartBottomBar(total) },
    ) { padding ->
        // TODO: [Session 3] Bài tập 2 - LazyColumn hiển thị cart items
        LazyColumn(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            itemsIndexed(cartItems) { index, item ->
                ProductItem(
                    cartItem = item,
                    onIncrease = {
                        cartItems[index] = item.copy(quantity = item.quantity + 1)
                    },
                    onDecrease = {
                        if (item.quantity > 0) {
                            cartItems[index] = item.copy(quantity = item.quantity - 1)
                        }
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShoppingCartScreenPreview() {
    ComposeTrainingTheme {
        ShoppingCartScreen()
    }
}
