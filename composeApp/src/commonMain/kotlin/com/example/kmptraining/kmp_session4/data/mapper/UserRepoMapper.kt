package com.example.kmptraining.kmp_session4.data.mapper

import com.example.kmptraining.kmp_session4.data.local.entity.UserRepoEntity
import com.example.kmptraining.kmp_session4.data.remote.dto.UserRepoDto
import com.example.kmptraining.kmp_session4.domain.model.OwnerModel
import com.example.kmptraining.kmp_session4.domain.model.UserRepoModel

fun UserRepoDto.toEntity(): UserRepoEntity {
    return UserRepoEntity(
        id = id ?: 0,
        nodeId = nodeId,
        name = name,
        fullName = fullName,
        isPrivate = private,
        htmlUrl = htmlUrl,
        description = description,
        fork = fork,

        url = url,
        forksUrl = forksUrl,
        keysUrl = keysUrl,
        collaboratorsUrl = collaboratorsUrl,
        teamsUrl = teamsUrl,
        hooksUrl = hooksUrl,
        issueEventsUrl = issueEventsUrl,
        eventsUrl = eventsUrl,

        createdAt = createdAt,
        updatedAt = updatedAt,
        pushedAt = pushedAt,

        gitUrl = gitUrl,
        sshUrl = sshUrl,
        cloneUrl = cloneUrl,
        svnUrl = svnUrl,
        homepage = homepage,

        size = size,
        stargazersCount = stargazersCount,
        watchersCount = watchersCount,
        language = language,

        hasIssues = hasIssues,
        hasProjects = hasProjects,
        hasDownloads = hasDownloads,
        hasWiki = hasWiki,
        hasPages = hasPages,
        hasDiscussions = hasDiscussions,

        forksCount = forksCount,
        mirrorUrl = mirrorUrl,
        archived = archived,
        disabled = disabled,
        openIssuesCount = openIssuesCount,

        allowForking = allowForking,
        isTemplate = isTemplate,
        webCommitSignoffRequired = webCommitSignoffRequired,

        visibility = visibility,
        forks = forks,
        openIssues = openIssues,
        watchers = watchers,
        defaultBranch = defaultBranch,

        topics = topics,

        owner = owner?.toEntity(),
        permissions = permissionsDto?.toEntity()
    )
}

fun UserRepoDto.toDomain(): UserRepoModel {
    return UserRepoModel(
        id = id ?: 0,
        name = name.orEmpty(),
        fullName = fullName.orEmpty(),
        description = description,
        isPrivate = private ?: false,

        htmlUrl = htmlUrl,
        owner = owner?.toDomain() ?: OwnerModel(0, "", null, null),

        isFork = fork ?: false,
        language = language,

        stars = stargazersCount ?: 0,
        forksCount = forksCount ?: 0,
        watchers = watchers ?: 0,

        openIssuesCount = openIssuesCount ?: 0,

        topics = topics ?: emptyList(),

        visibility = visibility,
        defaultBranch = defaultBranch,

        createdAt = createdAt,
        updatedAt = updatedAt,
        pushedAt = pushedAt,

        permissions = permissionsDto?.toDomain()
    )
}