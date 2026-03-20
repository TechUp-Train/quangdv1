package com.example.kmptraining.kmp_session4.data.mapper

import com.example.kmptraining.kmp_session4.data.local.entity.OwnerEntity
import com.example.kmptraining.kmp_session4.data.remote.dto.OwnerDto
import com.example.kmptraining.kmp_session4.domain.model.OwnerModel

fun OwnerDto.toEntity(): OwnerEntity {
    return OwnerEntity(
        login = login,
        id = id,
        nodeId = nodeId,
        avatarUrl = avatarUrl,
        gravatarId = gravatarId,
        url = url,
        htmlUrl = htmlUrl,
        followersUrl = followersUrl,
        followingUrl = followingUrl,
        gistsUrl = gistsUrl,
        starredUrl = starredUrl,
        subscriptionsUrl = subscriptionsUrl,
        organizationsUrl = organizationsUrl,
        reposUrl = reposUrl,
        eventsUrl = eventsUrl,
        receivedEventsUrl = receivedEventsUrl,
        type = type,
        siteAdmin = siteAdmin,
        userViewType = userViewType
    )
}

fun OwnerDto.toModel(): OwnerModel {
    return OwnerModel(
        id = id ?: 0,
        login = login.orEmpty(),
        avatarUrl = avatarUrl,
        htmlUrl = htmlUrl
    )
}

fun OwnerEntity.toModel(): OwnerModel {
    return OwnerModel(
        id = id ?: 0,
        login = login.orEmpty(),
        avatarUrl = avatarUrl,
        htmlUrl = htmlUrl
    )
}

fun OwnerModel.toEntity(existing: OwnerEntity?): OwnerEntity {
    return (existing ?: OwnerEntity()).copy(
        id = id,
        login = login,
        avatarUrl = avatarUrl,
        htmlUrl = htmlUrl
    )
}