package com.example.kmptraining.kmp_session4.data.mapper

import com.example.kmptraining.kmp_session4.data.local.entity.PublicRepoEntity
import com.example.kmptraining.kmp_session4.data.remote.dto.PublicRepoDto
import com.example.kmptraining.kmp_session4.domain.model.OwnerModel
import com.example.kmptraining.kmp_session4.domain.model.PublicRepoModel

fun PublicRepoDto.toEntity(): PublicRepoEntity {
    return PublicRepoEntity(
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
        assigneesUrl = assigneesUrl,
        branchesUrl = branchesUrl,
        tagsUrl = tagsUrl,
        blobsUrl = blobsUrl,
        gitTagsUrl = gitTagsUrl,
        gitRefsUrl = gitRefsUrl,
        treesUrl = treesUrl,
        statusesUrl = statusesUrl,
        languagesUrl = languagesUrl,
        stargazersUrl = stargazersUrl,
        contributorsUrl = contributorsUrl,
        subscribersUrl = subscribersUrl,
        subscriptionUrl = subscriptionUrl,
        commitsUrl = commitsUrl,
        gitCommitsUrl = gitCommitsUrl,
        commentsUrl = commentsUrl,
        issueCommentUrl = issueCommentUrl,
        contentsUrl = contentsUrl,
        compareUrl = compareUrl,
        mergesUrl = mergesUrl,
        archiveUrl = archiveUrl,
        downloadsUrl = downloadsUrl,
        issuesUrl = issuesUrl,
        pullsUrl = pullsUrl,
        milestonesUrl = milestonesUrl,
        notificationsUrl = notificationsUrl,
        labelsUrl = labelsUrl,
        releasesUrl = releasesUrl,
        deploymentsUrl = deploymentsUrl,
        owner = owner?.toEntity()
    )
}

fun PublicRepoDto.toModel(): PublicRepoModel {
    return PublicRepoModel(
        id = id ?: 0,
        name = name.orEmpty(),
        fullName = fullName.orEmpty(),
        description = description,
        isPrivate = private ?: false,
        htmlUrl = htmlUrl,
        owner = owner?.toModel() ?: OwnerModel(0, "", null, null),
        isFork = fork ?: false,
    )
}

fun PublicRepoEntity.toModel(): PublicRepoModel {
    return PublicRepoModel(
        id = id,
        name = name.orEmpty(),
        fullName = fullName.orEmpty(),
        description = description,
        isPrivate = isPrivate ?: false,
        htmlUrl = htmlUrl,
        owner = owner?.let { it.toModel() } ?: OwnerModel(0, "", null, null),
        isFork = fork ?: false,
    )
}