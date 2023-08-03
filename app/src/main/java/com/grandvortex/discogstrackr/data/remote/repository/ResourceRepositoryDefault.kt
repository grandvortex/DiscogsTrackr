package com.grandvortex.discogstrackr.data.remote.repository

import com.grandvortex.discogstrackr.data.mapper.toArtist
import com.grandvortex.discogstrackr.data.model.Artist
import com.grandvortex.discogstrackr.data.model.Label
import com.grandvortex.discogstrackr.data.remote.RemoteResult
import com.grandvortex.discogstrackr.data.remote.retrofit.RemoteService
import com.grandvortex.discogstrackr.data.remote.safeRemoteCall
import javax.inject.Inject

class ResourceRepositoryDefault @Inject constructor(private val remoteService: RemoteService) :
    ResourceRepository {

    override suspend fun getArtist(id: Int): RemoteResult<Artist> {
        return safeRemoteCall { remoteService.getArtist(id).toArtist() }
    }

    override suspend fun getLabel(id: Int): RemoteResult<Label> {
        TODO("Not yet implemented")
    }
}
