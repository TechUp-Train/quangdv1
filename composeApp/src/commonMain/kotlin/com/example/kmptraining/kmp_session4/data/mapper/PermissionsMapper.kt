package com.example.kmptraining.kmp_session4.data.mapper

import com.example.kmptraining.kmp_session4.data.local.entity.PermissionsEntity
import com.example.kmptraining.kmp_session4.data.remote.dto.PermissionsDto
import com.example.kmptraining.kmp_session4.domain.model.PermissionsModel

fun PermissionsDto.toEntity(): PermissionsEntity {
    return PermissionsEntity(
        admin = admin,
        maintain = maintain,
        push = push,
        triage = triage,
        pull = pull
    )
}

fun PermissionsEntity.toModel(): PermissionsModel {
    return PermissionsModel(
        canAdmin = admin ?: false,
        canMaintain = maintain ?: false,
        canPush = push ?: false,
        canTriage = triage ?: false,
        canPull = pull ?: false
    )
}

fun PermissionsModel.toEntity(existing: PermissionsEntity?): PermissionsEntity {
    return (existing ?: PermissionsEntity()).copy(
        admin = canAdmin,
        push = canPush,
        pull = canPull,
        maintain = canMaintain,
        triage = canTriage,
    )
}