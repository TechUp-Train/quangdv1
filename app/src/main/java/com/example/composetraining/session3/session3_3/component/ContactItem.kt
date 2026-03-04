package com.example.composetraining.session3.session3_3.component

import android.graphics.pdf.models.ListItem
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apero.composetraining.common.Contact

@Composable
fun ContactItem(contact: Contact) {
    ListItem(
        leadingContent = {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
        },
        headlineContent = { Text(contact.name) },
        supportingContent = { Text(contact.phone) },
        colors = ListItemDefaults.colors(
            containerColor = Color.White
        )
    )
}

@Preview(showBackground = true)
@Composable
fun ContactItemPreview() {
    ContactItem(
        contact = Contact(
            1,
            "Nguyễn Văn An",
            "0901234567",
            "an.nguyen@email.com",
            isFavorite = true,
            bio = "Android Developer tại Apero"
        )
    )
}