package com.example.composetraining.session5.session5_3

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.composetraining.session5.session5_3.component.ECommerceAppBar
import com.example.composetraining.session5.session5_3.data.Product
import com.example.composetraining.session5.session5_3.data.sampleCategories
import com.example.composetraining.session5.session5_3.data.sampleProducts
import com.example.composetraining.session5.session5_3.route.ProductFlowKey
import com.example.composetraining.session5.session5_3.screens.cart.CartScreen
import com.example.composetraining.session5.session5_3.screens.cart.DiscardCartDialog
import com.example.composetraining.session5.session5_3.screens.categoryList.CategoryListScreen
import com.example.composetraining.session5.session5_3.screens.productDetail.ProductDetailScreen
import com.example.composetraining.session5.session5_3.screens.productList.ProductListScreen
import com.example.composetraining.ui.theme.ComposeTrainingTheme

@Composable
fun ECommerceApp() {
    val backStack = rememberNavBackStack(ProductFlowKey.CategoryList)
    var selectedCategoryId by remember { mutableStateOf(sampleCategories.first().id) }
    var selectedProductId by remember { mutableStateOf(sampleProducts.first().id) }
    val cartItems = remember { mutableStateListOf<Product>() }
    var showConfirmDialog by remember { mutableStateOf(false) }

    val currentScreen = when (backStack.lastOrNull()) {
        is ProductFlowKey.CategoryList -> "Category List"
        is ProductFlowKey.ProductList -> "Product List"
        is ProductFlowKey.ProductDetail -> "Product Detail"
        is ProductFlowKey.Cart -> "Cart"
        else -> ""
    }

    val handleBack = {
        if (backStack.lastOrNull() is ProductFlowKey.Cart && cartItems.isNotEmpty()) {
            showConfirmDialog = true
        } else {
            backStack.removeLastOrNull()
        }
    }

    Scaffold(
        topBar = {
            ECommerceAppBar(
                title = currentScreen,
                onBack = { handleBack() },
                onCart = {
                    if (backStack.lastOrNull() !is ProductFlowKey.Cart) {
                        backStack.add(ProductFlowKey.Cart)
                    }
                },
                cartCount = cartItems.size
            )
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            NavDisplay(
                backStack = backStack,
                onBack = { handleBack() },
                entryProvider = entryProvider {
                    entry<ProductFlowKey.CategoryList> {
                        CategoryListScreen(
                            categories = sampleCategories,
                            onCategoryClick = { categoryId ->
                                backStack.add(ProductFlowKey.ProductList(categoryId))
                                selectedCategoryId = categoryId
                            },
                            cartCount = cartItems.size
                        )
                    }
                    entry<ProductFlowKey.ProductList> {
                        ProductListScreen(
                            products = sampleProducts.filter { it.categoryId == selectedCategoryId },
                            onProductClick = { product ->
                                selectedProductId = product.id
                                backStack.add(
                                    ProductFlowKey.ProductDetail(
                                        product.id,
                                        selectedCategoryId
                                    )
                                )
                            },
                            cartCount = cartItems.size
                        )
                    }
                    entry<ProductFlowKey.ProductDetail> {
                        ProductDetailScreen(
                            product = sampleProducts.first { it.id == selectedProductId },
                            onAddToCart = { product ->
                                cartItems.add(product)
                            },
                            onViewCart = {
                                if (backStack.lastOrNull() !is ProductFlowKey.Cart) {
                                    backStack.add(ProductFlowKey.Cart)
                                }
                            },
                            onBack = {
                                handleBack()
                            }
                        )
                    }
                    entry<ProductFlowKey.Cart> {
                        BackHandler(enabled = cartItems.isNotEmpty()) {
                            showConfirmDialog = true
                        }
                        CartScreen(
                            items = cartItems,
                            onBack = { handleBack() },
                            onRemoveItem = { product ->
                                cartItems.remove(product)
                            }
                        )
                    }
                }
            )

            if (showConfirmDialog) {
                DiscardCartDialog(
                    onDismiss = { showConfirmDialog = false },
                    onConfirm = {
                        showConfirmDialog = false
                        cartItems.clear()
                        backStack.removeLastOrNull()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ECommerceAppPreview() {
    ComposeTrainingTheme { ECommerceApp() }
}