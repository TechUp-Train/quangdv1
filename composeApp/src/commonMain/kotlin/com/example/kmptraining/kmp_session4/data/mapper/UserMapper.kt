package com.example.kmptraining.kmp_session4.data.mapper

import com.example.kmptraining.kmp_session4.data.local.entity.UserEntity
import com.example.kmptraining.kmp_session4.data.remote.dto.UserDto
import com.example.kmptraining.kmp_session4.data.remote.dto.OwnerDto
import com.example.kmptraining.kmp_session4.domain.model.UserModel

fun UserDto.toEntity(): UserEntity {
    return UserEntity(
        id = id ?: 0,
        login = login,
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
        name = name,
        company = company,
        blog = blog,
        location = location,
        email = email,
        hireable = hireable,
        bio = bio,
        twitterUsername = twitterUsername,
        publicRepos = publicRepos,
        publicGists = publicGists,
        followers = followers,
        following = following,
        createdAt = createdAt,
        updatedAt = updatedAt,
        userViewType = userViewType,
        notificationEmail = notificationEmail
    )
}

fun UserDto.toDomain(): UserModel {
    return UserModel(
        id = id ?: 0,
        login = login.orEmpty(),
        name = name ?: login.orEmpty(),
        avatarUrl = avatarUrl,
        bio = bio,
        location = location,
        blog = blog,
        followers = followers ?: 0,
        following = following ?: 0,
        publicRepos = publicRepos ?: 0,
        htmlUrl = htmlUrl
    )
}