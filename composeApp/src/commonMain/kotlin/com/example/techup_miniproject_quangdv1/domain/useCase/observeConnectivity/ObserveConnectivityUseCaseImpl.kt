package com.example.techup_miniproject_quangdv1.domain.useCase.observeConnectivity

import com.example.techup_miniproject_quangdv1.core.utils.ConnectivityMonitor
import com.example.techup_miniproject_quangdv1.core.utils.ConnectivityStatus
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class ObserveConnectivityUseCaseImpl(
    private val connectivityMonitor: ConnectivityMonitor,
) : ObserveConnectivityUseCase {
    override fun invoke(): Flow<ConnectivityStatus> = callbackFlow {
        connectivityMonitor.start()

        val job = launch {
            connectivityMonitor.status.collect {
                trySend(it)
            }
        }

        awaitClose {
            job.cancel()
            connectivityMonitor.stop()
        }
    }
}