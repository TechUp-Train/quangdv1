package com.example.composetraining.session3.session3_3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
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
import com.example.composetraining.session3.session3_3.component.SearchBar
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged

/**
 * ⭐⭐⭐ BÀI TẬP 3: Search & Filter Contacts (Challenge)
 *
 * Yêu cầu:
 * - TextField search bar (state hoisted lên SearchFilterScreen)
 * - Switch "Chỉ hiện active contacts" (dùng Contact.isFavorite làm active flag)
 * - LazyColumn: danh sách contacts đã filter (dùng derivedStateOf)
 * - Empty state: hiển thị "Không tìm thấy liên hệ nào" khi list trống
 * - rememberSaveable cho search query (survive xoay màn hình)
 * - snapshotFlow để debounce search 300ms (tránh filter mỗi keystroke)
 *
 * Tiêu chí:
 * - derivedStateOf đúng cách cho filter logic
 * - snapshotFlow + debounce(300ms) + distinctUntilChanged đúng cách
 * - UDF pattern: state xuống, events lên
 * - rememberSaveable cho query và toggle
 *
 * Gợi ý snapshotFlow:
 * LaunchedEffect(Unit) {
 *     snapshotFlow { searchQuery }     // chuyển Compose State → Flow
 *         .debounce(300L)              // đợi 300ms user ngừng gõ
 *         .distinctUntilChanged()      // bỏ qua nếu giá trị không đổi
 *         .collect { debouncedQuery = it }
 * }
 */

@OptIn(FlowPreview::class)
@Composable
fun SearchFilterScreen() {
    val contacts = SampleData.contacts

    // TODO: [Session 3] Bài tập 3 - Tạo state cho search query (rememberSaveable)
    var query by rememberSaveable { mutableStateOf("") }

    var debouncedQuery by remember { mutableStateOf("") }

    // TODO: [Session 3] Bài tập 3 - Tạo state cho toggle favorites
    var showFavoritesOnly by rememberSaveable { mutableStateOf(false) }

    // TODO: [Session 3] Bài tập 3 - Filter contacts bằng derivedStateOf
    val filteredContacts by remember {
        derivedStateOf {
            contacts.filter { contact ->
                val matchesQuery = contact.name.contains(debouncedQuery, ignoreCase = true)
                val matchesFavorite = if (showFavoritesOnly) contact.isFavorite else true
                matchesQuery && matchesFavorite
            }
        }
    }

    LaunchedEffect(Unit) {
        snapshotFlow { query }
            .debounce(300L)
            .distinctUntilChanged()
            .collectLatest { debouncedQuery = it }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bg_page)
            .padding(horizontal = 3.w, vertical = 1.h)
    ) {
        // TODO: [Session 3] Bài tập 3 - OutlinedTextField cho search
        // modifier = Modifier.fillMaxWidth().padding(16.dp)
        SearchBar(
            query = query,
            onQueryChange = { query = it },
            modifier = Modifier.fillMaxWidth(),
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
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 8.dp),
                            thickness = DividerDefaults.Thickness,
                            color = DividerDefaults.color
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
