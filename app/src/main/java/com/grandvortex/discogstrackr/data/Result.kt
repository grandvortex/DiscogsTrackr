package com.grandvortex.discogstrackr.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val e: Throwable) : Result<Nothing>()
}

fun <T> Flow<T>.asResult(): Flow<Result<T>> {
    return this
        .map<T, Result<T>> {
            Result.Success(it)
        }
        .catch { emit(Result.Error(it)) }
}

fun <T> Result<out T>.onSuccess(block: (data: T) -> Unit): Result<T> {
    if (this is Result.Success) {
        block.invoke(data)
    }
    return this
}

fun <T> Result<out T>.onError(block: (e: Throwable) -> Unit): Result<T> {
    if (this is Result.Error) {
        block.invoke(e)
    }
    return this
}


