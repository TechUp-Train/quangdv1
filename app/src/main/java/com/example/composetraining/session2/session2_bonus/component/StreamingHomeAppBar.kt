package com.example.composetraining.session2.session2_bonus.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.composetraining.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StreamingHomeAppBar(onSearchClick: () -> Unit) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.White
        ),
        title = {
            Text(
                stringResource(R.string.stream_app)
            )
        },
        actions = {
            IconButton(onSearchClick) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun StreamingHomeAppBarPreview() {
    StreamingHomeAppBar {  }
}