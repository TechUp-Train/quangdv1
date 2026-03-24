package com.example.techup_miniproject_quangdv1.core.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ConnectivityMonitorImpl(
    private val context: Context
) : ConnectivityMonitor {

    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    private val cm =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val _status = MutableStateFlow(ConnectivityStatus.Unavailable)
    override val status: StateFlow<ConnectivityStatus> = _status

    private val callback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) = updateNow()
        override fun onLost(network: Network) = updateNow()
    }

    private fun updateNow() {
        val caps = cm.getNetworkCapabilities(cm.activeNetwork)
        val online =
            caps?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true &&
                    caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)

        scope.launch {
            _status.value =
                if (online) ConnectivityStatus.Online else ConnectivityStatus.Offline
        }
    }

    override fun start() {
        updateNow()

        val req = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        cm.registerNetworkCallback(req, callback)
    }

    override fun stop() {
        cm.unregisterNetworkCallback(callback)
        scope.cancel()
    }
}

actual class ConnectivityMonitorFactory(
    private val context: Context
) {
    actual fun create(): ConnectivityMonitor =
        ConnectivityMonitorImpl(context)
}