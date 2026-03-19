package com.example.kmptraining.kmp_session4.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "public_repos")
data class PublicRepoEntity (
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
    val assigneesUrl: String? = null,
    val branchesUrl: String? = null,
    val tagsUrl: String? = null,
    val blobsUrl: String? = null,
    val gitTagsUrl: String? = null,
    val gitRefsUrl: String? = null,
    val treesUrl: String? = null,
    val statusesUrl: String? = null,
    val languagesUrl: String? = null,
    val stargazersUrl: String? = null,
    val contributorsUrl: String? = null,
    val subscribersUrl: String? = null,
    val subscriptionUrl: String? = null,
    val commitsUrl: String? = null,
    val gitCommitsUrl: String? = null,
    val commentsUrl: String? = null,
    val issueCommentUrl: String? = null,
    val contentsUrl: String? = null,
    val compareUrl: String? = null,
    val mergesUrl: String? = null,
    val archiveUrl: String? = null,
    val downloadsUrl: String? = null,
    val issuesUrl: String? = null,
    val pullsUrl: String? = null,
    val milestonesUrl: String? = null,
    val notificationsUrl: String? = null,
    val labelsUrl: String? = null,
    val releasesUrl: String? = null,
    val deploymentsUrl: String? = null,

    @Embedded(prefix = "owner_")
    val owner: OwnerEntity? = null
)