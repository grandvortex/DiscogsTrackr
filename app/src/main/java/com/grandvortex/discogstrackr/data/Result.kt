package com.grandvortex.discogstrackr.data

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val e: Throwable) : Result<Nothing>()
}
