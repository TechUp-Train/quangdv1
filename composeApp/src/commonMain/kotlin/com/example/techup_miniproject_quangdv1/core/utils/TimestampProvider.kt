package com.example.techup_miniproject_quangdv1.core.utils

import kotlinx.atomicfu.atomic
import kotlin.time.Clock

class TimestampProvider {

    private val offsetSeconds = atomic(0L)

    fun updateOffset(serverTimestampSeconds: Long) {
        val localSeconds = Clock.System.now().epochSeconds
        offsetSeconds.value = serverTimestampSeconds - localSeconds
    }

    fun getTimestamp(): Long {
        return Clock.System.now().epochSeconds + offsetSeconds.value
    }
}