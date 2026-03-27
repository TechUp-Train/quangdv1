package com.example.techup_miniproject_quangdv1.core.utils

import co.touchlab.kermit.Logger

object Log {
    const val APP = "APP"
    const val SERVICE = "SERVICE"
    const val REPOSITORY = "REPOSITORY"
    const val USE_CASE = "USE_CASE"
    const val VIEW_MODEL = "VIEW_MODEL"
    const val NETWORK = "NETWORK"
    const val DATA_SOURCE = "DATA_SOURCE"
    const val MAPPER = "MAPPER"
    const val UI = "UI"
    const val NAVIGATION = "NAVIGATION"
    const val CORE = "CORE"
    const val DI = "DI"
    const val SDK = "SDK"
    const val EVENT_BUS = "EVENT_BUS"
    const val VALIDATION = "VALIDATION"
    const val SECURITY = "SECURITY"
    const val CONFIG = "CONFIG"
    const val PERSISTENCE = "PERSISTENCE"
    const val AUTH = "AUTH"
    const val ANALYTICS = "ANALYTICS"
    const val STORAGE = "STORAGE"
    const val PERMISSION = "PERMISSION"
    const val PLATFORM = "PLATFORM"
    const val RESOURCE = "RESOURCE"

    fun d(
        tag: String,
        message: String,
    ) {
        Logger.withTag(tag).d { message }
    }

    fun i(
        tag: String,
        message: String,
    ) {
        Logger.withTag(tag).i { message }
    }

    fun w(
        tag: String,
        message: String,
    ) {
        Logger.withTag(tag).w { message }
    }

    fun e(
        tag: String,
        message: String,
        throwable: Throwable? = null,
    ) {
        Logger.withTag(tag).e(throwable) { message }
    }

    fun v(
        tag: String,
        message: String,
    ) {
        Logger.withTag(tag).v { message }
    }
}
