package com.example.kmptraining.migrate.session5.session5_2.route

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

// @Serializable data object HomeTabKey : NavKey
// @Serializable data class ArticleDetailKey(val articleId: Int) : NavKey
// ... thêm keys cho Explore và Profile tabs

enum class NavigationTab { HOME, EXPLORE, PROFILE }

@Serializable data object HomeTabKey : NavKey
@Serializable data class ArticleDetailKey(val articleId: Int) : NavKey
@Serializable data object ExploreTabKey : NavKey
@Serializable data class SearchResultKey(val query: String) : NavKey
@Serializable data object ProfileTabKey : NavKey
@Serializable data object EditProfileKey : NavKey