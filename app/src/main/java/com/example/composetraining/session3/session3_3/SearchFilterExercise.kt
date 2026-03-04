package com.example.composetraining.session3.session3_3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apero.composetraining.common.SampleData
import com.example.composetraining.ui.theme.ComposeTrainingTheme
import com.example.composetraining.R
import com.example.composetraining.common.bg_card
import com.example.composetraining.common.bg_page
import com.example.composetraining.common.border_strong
import com.example.composetraining.common.h
import com.example.composetraining.common.text_secondary
import com.example.composetraining.common.text_territory
import com.example.composetraining.common.w
import com.example.composetraining.session3.session3_3.component.ContactItem
import com.example.composetraining.session3.session3_3.component.EmptyContactView

/**
 * ⭐⭐⭐ BÀI TẬP 3: Search & Filter (Challenge)
 *
 * Yêu cầu:
 * - TextField search bar (state hoisted)
 * - LazyColumn: filtered contacts (derivedStateOf)
 * - Toggle: "Show favorites only" (Switch)
 * - Empty state: "No contacts found" khi list trống
 * - UDF pattern: data flows down, events flow up
 * - derivedStateOf cho filtered list
 * - rememberSaveable cho search query
 */

@Composable
fun SearchFilterScreen() {
    val contacts = SampleData.contacts

    // TODO: [Session 3] Bài tập 3 - Tạo state cho search query (rememberSaveable)
    var query by rememberSaveable { mutableStateOf("") }

    // TODO: [Session 3] Bài tập 3 - Tạo state cho toggle favorites
    var showFavoritesOnly by rememberSaveable { mutableStateOf(false) }

    // TODO: [Session 3] Bài tập 3 - Filter contacts bằng derivedStateOf
    val filteredContacts by remember {
        derivedStateOf {
            contacts.filter { contact ->
                val matchesQuery = contact.name.contains(query, ignoreCase = true)
                val matchesFavorite = if (showFavoritesOnly) contact.isFavorite else true
                matchesQuery && matchesFavorite
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bg_page)
            .padding(horizontal = 3.w, vertical = 1.h)
    ) {
        // TODO: [Session 3] Bài tập 3 - OutlinedTextField cho search
        // modifier = Modifier.fillMaxWidth().padding(16.dp)
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = query,
            onValueChange = { query = it },
            shape = RoundedCornerShape(15.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = text_territory
                )
            },
            placeholder = { Text(stringResource(R.string.search)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = bg_card,
                focusedBorderColor = border_strong,
                unfocusedContainerColor = bg_card,
                unfocusedBorderColor = border_strong,
                cursorColor = Color.Black
            )
        )

        // TODO: [Session 3] Bài tập 3 - Row chứa Text "Chỉ hiện favorites" + Switch
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 1.h),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                stringResource(R.string.fav_contacts_only),
                style = TextStyle(
                    color = text_secondary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Switch(
                checked = showFavoritesOnly,
                onCheckedChange = { showFavoritesOnly = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    uncheckedThumbColor = Color.White,
                    checkedTrackColor = MaterialTheme.colorScheme.primary,
                    uncheckedTrackColor = Color(0xFFE0DFDC),
                    checkedBorderColor = Color.Transparent,
                    uncheckedBorderColor = Color.Transparent
                )
            )
        }

        if (filteredContacts.isEmpty()) {
            // TODO: [Session 3] Bài tập 3 - Empty state khi filteredContacts rỗng
            // Text "Không tìm thấy liên hệ nào" + emoji
            EmptyContactView()
        } else {
            // TODO: [Session 3] Bài tập 3 - LazyColumn hiển thị filteredContacts
            // Mỗi item: ListItem(headlineContent = name, supportingContent = phone)
            LazyColumn(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .background(bg_card)
                    .fillMaxSize()
                    .padding(horizontal = 3.w),
                verticalArrangement = Arrangement.spacedBy(1.h)
            ) {
                itemsIndexed(filteredContacts) { index, contact ->
                    ContactItem(contact)

                    if (index < filteredContacts.lastIndex) {
                        Divider(
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
private fun SearchFilterScreenPreview() {
    ComposeTrainingTheme { SearchFilterScreen() }
}
