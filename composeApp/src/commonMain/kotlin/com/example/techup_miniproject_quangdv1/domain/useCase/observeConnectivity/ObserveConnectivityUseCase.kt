package com.example.techup_miniproject_quangdv1.domain.useCase.observeConnectivity

import com.example.techup_miniproject_quangdv1.core.utils.ConnectivityStatus
import kotlinx.coroutines.flow.Flow

interface ObserveConnectivityUseCase {
    operator fun invoke(): Flow<ConnectivityStatus>
}