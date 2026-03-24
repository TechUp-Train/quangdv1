package com.example.techup_miniproject_quangdv1.core.utils

import kotlinx.coroutines.flow.MutableStateFlow

sealed class ResponseStatus<out T> {
    data class Success<out T>(val data: T) : ResponseStatus<T>()

    data class Error(val message: String = "", val errorCode: Int = 500) : ResponseStatus<Nothing>()

    data object Loading : ResponseStatus<Nothing>()

    data object Idle : ResponseStatus<Nothing>()
}

fun <T> ResponseStatus<T>.observer(response: MutableStateFlow<ResponseStatus<T>>) {
    when (this) {
        is ResponseStatus.Loading -> {
            response.value = ResponseStatus.Loading
        }

        is ResponseStatus.Success -> {
            response.value = ResponseStatus.Success(this.data)
        }

        is ResponseStatus.Error -> {
            response.value = ResponseStatus.Error(message = this.message)
        }
        else -> {}
    }
}
