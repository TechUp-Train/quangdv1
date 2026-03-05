package com.example.composetraining.session3.session3_3.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.composetraining.R
import com.example.composetraining.common.bg_card
import com.example.composetraining.common.border_strong
import com.example.composetraining.common.text_territory

@Composable
fun SearchBar(query: String, onQueryChange: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = query,
        onValueChange = onQueryChange,
        shape = RoundedCornerShape(15.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search, contentDescription = null, tint = text_territory
            )
        },
        placeholder = { Text(stringResource(R.string.search)) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = bg_card,
            focusedBorderColor = border_strong,
            unfocusedContainerColor = bg_card,
            unfocusedBorderColor = border_strong,
            cursorColor = Color.Black
        ),
        singleLine = true,
    )
}