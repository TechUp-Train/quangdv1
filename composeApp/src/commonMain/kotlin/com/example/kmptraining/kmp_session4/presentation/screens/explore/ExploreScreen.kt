package com.example.kmptraining.kmp_session4.presentation.screens.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kmptraining.kmp_session4.core.utils.ResponseStatus
import com.example.kmptraining.kmp_session4.domain.model.RepoModel
import com.example.kmptraining.kmp_session4.presentation.components.ErrorState
import com.example.kmptraining.kmp_session4.presentation.components.LoadingState
import com.example.kmptraining.kmp_session4.presentation.screens.explore.components.SearchRepoItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ExploreScreen(
    exploreViewModel: ExploreViewModel = koinViewModel<ExploreViewModel>(),
    onRepoClick: (RepoModel) -> Unit
) {
    val searchText by exploreViewModel.searchText.collectAsState()
    val selectedTab by exploreViewModel.selectedTab.collectAsState()
    val searchResults by exploreViewModel.searchResults.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0D1117), // GitHub dark background
                        Color(0xFF161B22)
                    )
                )
            )
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Search Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* Handle back */ }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .background(Color(0xFF161B22), RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color(0xFF8B949E),
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        
                        BasicTextField(
                            value = searchText,
                            onValueChange = { exploreViewModel.onSearchQueryChanged(it) },
                            textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                            cursorBrush = SolidColor(Color(0xFF4DA3FF)),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                            keyboardActions = KeyboardActions(onSearch = {
                                keyboardController?.hide()
                            }),
                            modifier = Modifier.fillMaxWidth(),
                            decorationBox = { innerTextField ->
                                if (searchText.isEmpty()) {
                                    Text(
                                        text = "Search GitHub",
                                        color = Color(0xFF8B949E),
                                        fontSize = 16.sp
                                    )
                                }
                                innerTextField()
                            }
                        )
                    }
                }

                TextButton(onClick = { exploreViewModel.onSearchQueryChanged("") }) {
                    Text(text = "Cancel", color = Color(0xFF4DA3FF))
                }
            }

            // Tabs
            ScrollableTabRow(
                selectedTabIndex = selectedTab.ordinal,
                containerColor = Color.Transparent,
                contentColor = Color.White,
                edgePadding = 16.dp,
                divider = { HorizontalDivider(thickness = 0.5.dp, color = Color(0xFF30363D)) },
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTab.ordinal]),
                        color = Color(0xFF4DA3FF)
                    )
                }
            ) {
                SearchTab.entries.forEach { tab ->
                    Tab(
                        selected = selectedTab == tab,
                        onClick = { exploreViewModel.onTabSelected(tab) },
                        text = {
                            Text(
                                text = tab.title,
                                fontSize = 14.sp,
                                fontWeight = if (selectedTab == tab) FontWeight.Bold else FontWeight.Normal,
                                color = if (selectedTab == tab) Color.White else Color(0xFF8B949E)
                            )
                        }
                    )
                }
            }

            // Results
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                when (searchResults) {
                    is ResponseStatus.Loading -> {
                        item { LoadingState() }
                    }
                    is ResponseStatus.Error -> {
                        item {
                            ErrorState(
                                message = (searchResults as ResponseStatus.Error).message,
                                onRetry = { /* Retry logic */ }
                            )
                        }
                    }
                    is ResponseStatus.Success -> {
                        val data = (searchResults as ResponseStatus.Success).data
                        if (data.isEmpty() && searchText.isNotEmpty()) {
                            item {
                                Box(
                                    modifier = Modifier.fillParentMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = "No results found for \"$searchText\"", color = Color.Gray)
                                }
                            }
                        } else {
                            items(data) { repo ->
                                SearchRepoItem(repo = repo, onClick = { onRepoClick(repo) })
                                HorizontalDivider(thickness = 0.5.dp, color = Color(0xFF30363D))
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ExploreScreenPreview() {
    ExploreScreen(onRepoClick = {})
}