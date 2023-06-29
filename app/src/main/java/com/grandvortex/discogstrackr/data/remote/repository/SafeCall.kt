package com.grandvortex.discogstrackr.data.remote.repository

import com.grandvortex.discogstrackr.data.Result

suspend fun <T> safeNetworkCall(networkCall: suspend () -> T): Result<T> {
    return try {
        Result.Success(networkCall())
    } catch (throwable: Throwable) {
        Result.Error(throwable)
    }
}
