package com.grandvortex.discogstrackr.data.remote

import com.grandvortex.discogstrackr.BuildConfig

data class BaseUrl(val value: String)

// Factory for HTTP authorization credentials.
object Credentials {
    fun keyAndSecret(
        key: String = BuildConfig.KEY, secret: String = BuildConfig.SECRET
    ): String {
        return "Discogs key=$key, secret=$secret"
    }
}

suspend fun <T> safeRemoteCall(remoteCall: suspend () -> T): RemoteResult<T> {
    return try {
        RemoteResult.Success(remoteCall())
    } catch (throwable: Throwable) {
        RemoteResult.Error(throwable)
    }
}
