package com.example.composetraining.session3.session3_3.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetraining.R
import com.example.composetraining.common.h
import com.example.composetraining.common.text_secondary
import com.example.composetraining.common.text_territory

@Preview(showBackground = true)
@Composable
fun EmptyContactView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = Icons.Default.Clear,
            contentDescription = null,
            modifier = Modifier.padding(bottom = 16.dp),
            tint = text_territory,
        )
        Text(
            stringResource(R.string.no_contact_found),
            style =
                TextStyle(
                    color = text_secondary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                ),
        )
        Spacer(modifier = Modifier.height(2.h))
        Text(
            stringResource(R.string.try_other_filter),
            style =
                TextStyle(
                    color = text_territory,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                ),
            maxLines = 2,
        )
    }
}
