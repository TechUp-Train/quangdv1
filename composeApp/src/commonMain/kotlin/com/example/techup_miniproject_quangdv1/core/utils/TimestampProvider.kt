package com.example.techup_miniproject_quangdv1.core.utils

import kotlinx.atomicfu.atomic
import kotlin.time.Clock

/**
 * Centrally manages server-synchronized timestamps to ensure valid API signatures.
 *
 * This provider calculates the offset between the local system clock and the server clock
 * to keep the `x-api-timestamp` header within the allowed 4-minute drift window.
 */
class TimestampProvider {

    // Atomic long to store the offset (serverTime - localTime) in seconds.
    private val offsetSeconds = atomic(0L)

    /**
     * Updates the local time offset based on a fresh server timestamp.
     * Call this after fetching a timestamp from the [com.example.techup_miniproject_quangdv1.data.remote.service.ApiService.TimestampService].
     */
    fun updateOffset(serverTimestampSeconds: Long) {
        val localSeconds = Clock.System.now().epochSeconds
        offsetSeconds.value = serverTimestampSeconds - localSeconds
    }

    /**
     * Returns the current server-synchronized timestamp in epoch seconds.
     */
    fun getTimestamp(): Long {
        return Clock.System.now().epochSeconds + offsetSeconds.value
    }
}