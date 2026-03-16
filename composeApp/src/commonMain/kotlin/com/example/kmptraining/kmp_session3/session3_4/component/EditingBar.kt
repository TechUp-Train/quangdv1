package com.example.kmptraining.kmp_session3.session3_4.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import kmptraining.composeapp.generated.resources.Res
import kmptraining.composeapp.generated.resources.ic_crop
import kmptraining.composeapp.generated.resources.ic_filter
import kmptraining.composeapp.generated.resources.ic_rotate
import org.jetbrains.compose.resources.painterResource

@Composable
fun EditingBar(onCrop: () -> Unit, onRotate: () -> Unit, onFilter: () -> Unit) {
    BottomAppBar {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            EditingBarItem(
                icon = painterResource(Res.drawable.ic_crop),
                label = "Crop",
                onClick = onCrop
            )

            EditingBarItem(
                icon = painterResource(Res.drawable.ic_rotate),
                label = "Rotate",
                onClick = onRotate
            )

            EditingBarItem(
                icon = painterResource(Res.drawable.ic_filter),
                label = "Filter",
                onClick = onFilter
            )
        }
    }
}

@Composable
fun EditingBarItem(
    icon: Painter,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(onClick = onClick) {
            Icon(
                painter = icon,
                contentDescription = label
            )
        }

        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EditingBarPreview() {
    EditingBar(onCrop = {}, onRotate = {}, onFilter = {})
}