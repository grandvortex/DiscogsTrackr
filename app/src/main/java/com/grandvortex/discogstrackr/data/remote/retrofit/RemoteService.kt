package com.grandvortex.discogstrackr.data.remote.retrofit

import com.grandvortex.discogstrackr.data.remote.dto.ArtistDTO
import com.grandvortex.discogstrackr.data.remote.dto.LabelDTO
import com.grandvortex.discogstrackr.data.remote.dto.SearchResultsDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteService {
    @GET("database/search")
    suspend fun search(@Query("q") query: String): SearchResultsDTO

    @GET("artists/{artist_id}")
    suspend fun getArtist(@Path("artist_id") artistID: Int): ArtistDTO

    @GET("labels/{label_id}")
    suspend fun getLabel(@Path("label_id") labelID: Int): LabelDTO
}
