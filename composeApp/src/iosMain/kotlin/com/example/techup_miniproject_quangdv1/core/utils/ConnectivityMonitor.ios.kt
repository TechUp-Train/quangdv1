package com.example.techup_miniproject_quangdv1.core.utils

import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.nativeHeap
import kotlinx.cinterop.ptr
import kotlinx.cinterop.staticCFunction
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import platform.CoreFoundation.CFRunLoopGetMain
import platform.CoreFoundation.kCFRunLoopDefaultMode
import platform.SystemConfiguration.*
import platform.posix.sockaddr
import kotlinx.cinterop.*
import platform.posix.*

class ConnectivityMonitorImpl : ConnectivityMonitor {

    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    private val _status = MutableStateFlow(ConnectivityStatus.Unavailable)
    override val status: StateFlow<ConnectivityStatus> = _status

    @OptIn(ExperimentalForeignApi::class)
    private var reachability: SCNetworkReachabilityRef? = null

    @OptIn(ExperimentalForeignApi::class)
    override fun start() {
        memScoped {
            val zero = alloc<sockaddr>()
            zero.sa_family = AF_INET.convert()

            reachability = SCNetworkReachabilityCreateWithAddress(null, zero.ptr)
        }

        val callback = staticCFunction<
                SCNetworkReachabilityRef?,
                SCNetworkReachabilityFlags,
                COpaquePointer?,
                Unit
                > { _, flags, _ ->

            val reachable =
                flags.toInt() and kSCNetworkReachabilityFlagsReachable.toInt() != 0

            val needsConn =
                flags.toInt() and kSCNetworkReachabilityFlagsConnectionRequired.toInt() != 0

            val online = reachable && !needsConn

            scope.launch {
                _status.value =
                    if (online) ConnectivityStatus.Online else ConnectivityStatus.Offline
            }
        }

        val context = nativeHeap.alloc<SCNetworkReachabilityContext>()

        SCNetworkReachabilitySetCallback(
            reachability,
            callback,
            context.ptr
        )

        SCNetworkReachabilityScheduleWithRunLoop(
            reachability,
            CFRunLoopGetMain(),
            kCFRunLoopDefaultMode
        )
    }

    @OptIn(ExperimentalForeignApi::class)
    override fun stop() {
        reachability?.let {
            SCNetworkReachabilityUnscheduleFromRunLoop(
                it,
                CFRunLoopGetMain(),
                kCFRunLoopDefaultMode
            )
        }
        scope.cancel()
    }
}

actual class ConnectivityMonitorFactory {
    actual fun create(): ConnectivityMonitor = ConnectivityMonitorImpl()
}