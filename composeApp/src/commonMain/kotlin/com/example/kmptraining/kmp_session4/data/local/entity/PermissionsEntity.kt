package com.example.kmptraining.kmp_session4.data.local.entity

data class PermissionsEntity(
    val id: Int = 0,

    val admin: Boolean? = null,
    val maintain: Boolean? = null,
    val push: Boolean? = null,
    val triage: Boolean? = null,
    val pull: Boolean? = null
)