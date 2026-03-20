package com.example.kmptraining.kmp_session4.data.mapper

import com.example.kmptraining.kmp_session4.data.local.entity.RepoEntity
import com.example.kmptraining.kmp_session4.data.remote.dto.RepoDto
import com.example.kmptraining.kmp_session4.domain.model.OwnerModel
import com.example.kmptraining.kmp_session4.domain.model.RepoModel

fun RepoDto.toEntity(repoType: String = "PUBLIC"): RepoEntity {
    return RepoEntity(
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
        permissions = permissionsDto?.toEntity(),
        repoType = repoType
    )
}

fun RepoDto.toModel(): RepoModel {
    return this.toEntity().toModel()
}


fun RepoEntity.toModel(): RepoModel {
    return RepoModel(
        id = id,
        name = name.orEmpty(),
        fullName = fullName.orEmpty(),
        description = description,
        isPrivate = isPrivate ?: false,

        htmlUrl = htmlUrl,

        owner = owner?.toModel()
            ?: OwnerModel(0, "", null, null),

        isFork = fork ?: false,
        language = language,

        stars = stargazersCount ?: 0,
        forksCount = forksCount ?: 0,
        watchers = watchersCount ?: 0,

        openIssuesCount = openIssuesCount ?: 0,

        topics = topics ?: emptyList(),

        visibility = visibility,
        defaultBranch = defaultBranch,

        createdAt = createdAt,
        updatedAt = updatedAt,
        pushedAt = pushedAt,

        permissions = permissions?.toModel()
    )
}

fun RepoModel.toEntity(existing: RepoEntity): RepoEntity {
    return existing.copy(
        name = name,
        fullName = fullName,
        description = description,
        isPrivate = isPrivate,

        htmlUrl = htmlUrl,

        fork = isFork,
        language = language,

        stargazersCount = stars,
        forksCount = forksCount,
        watchersCount = watchers,

        openIssuesCount = openIssuesCount,

        topics = topics,

        visibility = visibility,
        defaultBranch = defaultBranch,

        createdAt = createdAt,
        updatedAt = updatedAt,
        pushedAt = pushedAt,

        owner = owner.toEntity(existing.owner),
        permissions = permissions?.toEntity(existing.permissions)
    )
}