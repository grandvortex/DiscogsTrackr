package com.grandvortex.discogstrackr.data.remote.retrofit

import com.grandvortex.discogstrackr.data.remote.model.SearchResultsRemote
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {
    @GET("database/search")
    suspend fun search(@Query("q") query: String): SearchResultsRemote
}
