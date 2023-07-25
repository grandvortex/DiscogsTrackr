package com.grandvortex.discogstrackr.data.remote

suspend fun <T> safeRemoteCall(remoteCall: suspend () -> T): RemoteResult<T> {
    return try {
        RemoteResult.Success(remoteCall())
    } catch (throwable: Throwable) {
        RemoteResult.Error(throwable)
    }
}
