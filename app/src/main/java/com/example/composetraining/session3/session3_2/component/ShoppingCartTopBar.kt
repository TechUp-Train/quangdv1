package com.example.composetraining.session3.session3_2.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.composetraining.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingCartTopBar() {
    TopAppBar(
        title = {
            Row {
                Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null)
                Text(
                    stringResource(R.string.shopping_cart), style = TextStyle(
                        color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold
                    )
                )
            }
        }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.Black,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White
        )
    )
}