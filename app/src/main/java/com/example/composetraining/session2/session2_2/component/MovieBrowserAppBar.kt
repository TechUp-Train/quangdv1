package com.example.composetraining.session2.session2_2.component

import androidx.compose.material3.ExperimentalMaterial3Api
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
fun MovieBrowserAppBar() {
    TopAppBar(
        colors =
            TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
            ),
        title = { Text(stringResource(R.string.movies)) },
    )
}

@Preview(showBackground = true)
@Composable
fun MovieBrowserAppBarPreview() {
    MovieBrowserAppBar()
}
