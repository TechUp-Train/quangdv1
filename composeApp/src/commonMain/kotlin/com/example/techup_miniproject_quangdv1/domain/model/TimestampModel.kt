package com.example.techup_miniproject_quangdv1.domain.model

/**
 * Domain model representing the server timestamp.
 * Used to synchronize client time with the server for API signature generation.
 *
 * @property timestamp The server's current Unix timestamp in milliseconds.
 */
data class TimestampModel(
    val timestamp: Long,
)
