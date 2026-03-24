package com.example.techup_miniproject_quangdv1.core.utils


import kotlinx.coroutines.flow.StateFlow

enum class ConnectivityStatus {
    Online, Offline, Unavailable
}

interface ConnectivityMonitor {
    val status: StateFlow<ConnectivityStatus>
    fun start()
    fun stop()
}

expect class ConnectivityMonitorFactory {
    fun create(): ConnectivityMonitor
}