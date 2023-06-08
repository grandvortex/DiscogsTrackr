package com.grandvortex.discogstrackr.data.remote.repository

import retrofit2.HttpException

sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class HttpError(val code: Int, val message: String) : NetworkResult<Nothing>()
    data class NetworkError(val e: Throwable) : NetworkResult<Nothing>()
}

suspend fun <T> safeNetworkCall(networkCall: suspend () -> T): NetworkResult<T> {
    return try {
        NetworkResult.Success(networkCall())
    } catch (throwable: Throwable) {
        when (throwable) {
            is HttpException -> {
                val code = throwable.code()
                val message = throwable.message()
                NetworkResult.HttpError(code, message)
            }

            else -> NetworkResult.NetworkError(throwable)
        }
    }
}
