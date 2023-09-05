package com.grandvortex.discogstrackr.data.remote

import com.grandvortex.discogstrackr.BuildConfig
import com.grandvortex.discogstrackr.data.Result
import com.squareup.moshi.Moshi

data class BaseUrl(val value: String)

// Factory for HTTP authorization credentials.
object Credentials {
    fun keyAndSecret(
        key: String = BuildConfig.KEY, secret: String = BuildConfig.SECRET
    ): String {
        return "Discogs key=$key, secret=$secret"
    }
}

suspend fun <T> safeRemoteCall(remoteCall: suspend () -> T): Result<T> {
    return try {
        Result.Success(remoteCall())
    } catch (throwable: Throwable) {
        Result.Error(throwable)
    }
}

inline fun <reified T> Moshi.toJson(data: T): String? = adapter(T::class.java).toJson(data)

inline fun <reified T> Moshi.fromJson(json: String): T? = adapter(T::class.java).fromJson(json)
