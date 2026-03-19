package com.example.kmptraining.kmp_session4.data.local.entity

import androidx.room.*

@Entity(tableName = "user_repos")
data class UserRepoEntity(
    @PrimaryKey
    val id: Int,

    val nodeId: String? = null,
    val name: String? = null,
    val fullName: String? = null,
    val isPrivate: Boolean? = null,
    val htmlUrl: String? = null,
    val description: String? = null,
    val fork: Boolean? = null,

    val url: String? = null,
    val forksUrl: String? = null,
    val keysUrl: String? = null,
    val collaboratorsUrl: String? = null,
    val teamsUrl: String? = null,
    val hooksUrl: String? = null,
    val issueEventsUrl: String? = null,
    val eventsUrl: String? = null,

    val createdAt: String? = null,
    val updatedAt: String? = null,
    val pushedAt: String? = null,

    val gitUrl: String? = null,
    val sshUrl: String? = null,
    val cloneUrl: String? = null,
    val svnUrl: String? = null,
    val homepage: String? = null,

    val size: Int? = null,
    val stargazersCount: Int? = null,
    val watchersCount: Int? = null,
    val language: String? = null,

    val hasIssues: Boolean? = null,
    val hasProjects: Boolean? = null,
    val hasDownloads: Boolean? = null,
    val hasWiki: Boolean? = null,
    val hasPages: Boolean? = null,
    val hasDiscussions: Boolean? = null,

    val forksCount: Int? = null,
    val mirrorUrl: String? = null,
    val archived: Boolean? = null,
    val disabled: Boolean? = null,
    val openIssuesCount: Int? = null,

    val allowForking: Boolean? = null,
    val isTemplate: Boolean? = null,
    val webCommitSignoffRequired: Boolean? = null,

    val visibility: String? = null,
    val forks: Int? = null,
    val openIssues: Int? = null,
    val watchers: Int? = null,
    val defaultBranch: String? = null,

    val topics: List<String>? = null,

    @Embedded(prefix = "owner_")
    val owner: OwnerEntity? = null,

    @Embedded(prefix = "permissions_")
    val permissions: PermissionsEntity? = null
)