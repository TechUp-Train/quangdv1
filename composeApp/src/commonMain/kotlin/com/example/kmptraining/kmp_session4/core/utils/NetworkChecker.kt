package com.example.kmptraining.kmp_session4.core.utils

import kotlinx.coroutines.flow.Flow

expect class NetworkChecker {
    fun observe(): Flow<Boolean>
}