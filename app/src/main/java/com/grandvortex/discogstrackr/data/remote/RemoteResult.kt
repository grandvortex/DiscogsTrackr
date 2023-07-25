package com.grandvortex.discogstrackr.data.remote

sealed class RemoteResult<out T> {
    data class Success<out T>(val data: T) : RemoteResult<T>()
    data class Error(val e: Throwable) : RemoteResult<Nothing>()
}
