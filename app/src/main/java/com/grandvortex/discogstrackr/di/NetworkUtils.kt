package com.grandvortex.discogstrackr.di

import com.grandvortex.discogstrackr.BuildConfig

data class BaseUrl(val value: String)

// Factory for HTTP authorization credentials.
object Credentials {
    fun keyAndSecret(
        key: String = BuildConfig.KEY,
        secret: String = BuildConfig.SECRET
    ): String {
        return "Discogs key=$key, secret=$secret"
    }
}
