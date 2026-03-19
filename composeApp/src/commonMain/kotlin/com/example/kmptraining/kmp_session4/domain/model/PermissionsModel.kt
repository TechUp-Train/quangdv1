package com.example.kmptraining.kmp_session4.domain.model

data class PermissionsModel(
    val canAdmin: Boolean,
    val canMaintain: Boolean,
    val canPush: Boolean,
    val canTriage: Boolean,
    val canPull: Boolean
)