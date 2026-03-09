package com.example.composetraining.session5.session5_2

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.composetraining.session5.session5_2.component.AppBottomBar
import com.example.composetraining.session5.session5_2.route.*
import com.example.composetraining.session5.session5_2.screens.explore.ExploreScreen
import com.example.composetraining.session5.session5_2.screens.explore.SearchResultScreen
import com.example.composetraining.session5.session5_2.screens.home.ArticleDetailScreen
import com.example.composetraining.session5.session5_2.screens.home.HomeScreen
import com.example.composetraining.session5.session5_2.screens.profile.EditProfileScreen
import com.example.composetraining.session5.session5_2.screens.profile.ProfileScreen
import com.example.composetraining.ui.theme.ComposeTrainingTheme

/**
 * ⭐⭐ BÀI TẬP 2: Tab App với per-tab back stacks (Medium — 60 phút)
 *
 * Key insight: Navigation 3 = back stack là List → mỗi tab có List riêng!
 *
 * Yêu cầu:
 * - 3 tabs: Home / Explore / Profile
 * - Mỗi tab có back stack RIÊNG (3 rememberNavBackStack)
 * - Scaffold + NavigationBar ở dưới
 * - Home tab: có thể navigate vào ArticleDetailKey(articleId)
 * - Explore tab: có thể navigate vào SearchResultKey(query)
 * - Profile tab: có thể navigate vào EditProfileKey
 * - Switch tab: back stack của tab cũ được GIỮ NGUYÊN (không reset)
 * - Back ở root của tab: không crash (backStack.size == 1 → không pop)
 *
 * Gợi ý cấu trúc:
 * ```kotlin
 * // Keys — cần @Serializable + NavKey
 * @Serializable data object HomeTabKey : NavKey
 * @Serializable data class ArticleDetailKey(val articleId: Int) : NavKey
 * @Serializable data object ExploreTabKey : NavKey
 * @Serializable data class SearchResultKey(val query: String) : NavKey
 * @Serializable data object ProfileTabKey : NavKey
 * @Serializable data object EditProfileKey : NavKey
 *
 * enum class Tab { HOME, EXPLORE, PROFILE }
 *
 * // 3 back stacks riêng biệt — QUAN TRỌNG
 * val homeStack = rememberNavBackStack(HomeTabKey)
 * val exploreStack = rememberNavBackStack(ExploreTabKey)
 * val profileStack = rememberNavBackStack(ProfileTabKey)
 *
 * var selectedTab by rememberSaveable { mutableStateOf(Tab.HOME) }
 * val currentStack = when (selectedTab) {
 *     Tab.HOME -> homeStack
 *     Tab.EXPLORE -> exploreStack
 *     Tab.PROFILE -> profileStack
 * }
 *
 * Scaffold(bottomBar = { TabBar(selectedTab, onTabSelect = { selectedTab = it }) }) { padding ->
 *     NavDisplay(
 *         backStack = currentStack,
 *         onBack = { if (currentStack.size > 1) currentStack.removeLastOrNull() },
 *         entryProvider = entryProvider {
 *             entry<HomeTabKey> { HomeTabScreen(onArticleClick = { homeStack.add(ArticleDetailKey(it)) }) }
 *             entry<ArticleDetailKey> { key -> ArticleDetailScreen(articleId = key.articleId) }
 *             // ... entries cho các tab khác
 *         }
 *     )
 * }
 * ```
 *
 * Tiêu chí nghiệm thu:
 * - Switch tab giữ back stack của tab cũ
 * - Back ở tab root không crash
 * - Navigate vào detail rồi switch tab, quay lại tab → vẫn thấy detail
 */

@Composable
fun TabAppScreen() {
    val homeStack = rememberNavBackStack(HomeTabKey)
    val exploreStack = rememberNavBackStack(ExploreTabKey)
    val profileStack = rememberNavBackStack(ProfileTabKey)

    var selectedTab by rememberSaveable { mutableStateOf(NavigationTab.HOME) }
    val currentStack = when (selectedTab) {
        NavigationTab.HOME -> homeStack
        NavigationTab.EXPLORE -> exploreStack
        NavigationTab.PROFILE -> profileStack
    }

    Scaffold(
        bottomBar = { AppBottomBar(selectedTab, onTabSelect = { selectedTab = it }) }
    ) { contentPadding ->
        NavDisplay(
            modifier = Modifier.padding(contentPadding),
            backStack = currentStack,
            onBack = { if (currentStack.size > 1) currentStack.removeLastOrNull() },
            entryProvider = entryProvider {
                entry<HomeTabKey> {
                    HomeScreen(onArticleClick = { articleId ->
                        homeStack.add(ArticleDetailKey(articleId))
                    })
                }
                entry<ArticleDetailKey> { key ->
                    ArticleDetailScreen(
                        articleId = key.articleId,
                        onBack = { homeStack.removeLastOrNull() }
                    )
                }

                entry<ExploreTabKey> {
                    ExploreScreen(onSearch = { query ->
                        exploreStack.add(SearchResultKey(query))
                    })
                }
                entry<SearchResultKey> { key ->
                    SearchResultScreen(
                        query = key.query,
                        onBack = { exploreStack.removeLastOrNull() },
                        onArticleClick = { articleId ->
                            exploreStack.add(ArticleDetailKey(articleId))
                        }
                    )
                }

                entry<ProfileTabKey> {
                    ProfileScreen(onEditProfile = {
                        profileStack.add(EditProfileKey)
                    })
                }
                entry<EditProfileKey> {
                    EditProfileScreen(onBack = {
                        profileStack.removeLastOrNull()
                    })
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TabAppScreenPreview() {
    ComposeTrainingTheme { TabAppScreen() }
}
