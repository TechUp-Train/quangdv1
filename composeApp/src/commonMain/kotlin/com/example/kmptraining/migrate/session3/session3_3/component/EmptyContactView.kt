package com.example.kmptraining.migrate.session3.session3_3.component

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kmptraining.migrate.common.text_secondary
import com.example.kmptraining.migrate.common.text_territory
import kmptraining.composeapp.generated.resources.Res
import kmptraining.composeapp.generated.resources.no_contact_found
import kmptraining.composeapp.generated.resources.try_other_filter
import org.jetbrains.compose.resources.stringResource

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
            stringResource(Res.string.no_contact_found),
            style =
                TextStyle(
                    color = text_secondary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                ),
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            stringResource(Res.string.try_other_filter),
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
