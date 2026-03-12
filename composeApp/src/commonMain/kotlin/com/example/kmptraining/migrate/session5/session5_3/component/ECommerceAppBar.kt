package com.example.kmptraining.migrate.session5.session5_3.component

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.kmptraining.migrate.ui.theme.ComposeTrainingTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ECommerceAppBar(
    title: String,
    onBack: () -> Unit,
    onCart: () -> Unit,
    modifier: Modifier = Modifier,
    cartCount: Int = 0,
) {
    TopAppBar(
        modifier = modifier.background(Color.Transparent),
        title = {
            Text(text = title)
        },
        navigationIcon = {
            if (title != "Category List") {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)
                }
            }
        },
        actions = {
            IconButton(onClick = onCart) {
                BadgedBox(
                    badge = {
                        if (cartCount > 0) {
                            ECommerceBadge(count = cartCount)
                        }
                    }
                ) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = null)
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun ECommerceAppBarPreview() {
    ComposeTrainingTheme {
        ECommerceAppBar(
            title = "Product Detail",
            onBack = {},
            onCart = {},
            cartCount = 5
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ECommerceAppBarCategoryPreview() {
    ComposeTrainingTheme {
        ECommerceAppBar(
            title = "Category List",
            onBack = {},
            onCart = {},
            cartCount = 0
        )
    }
}