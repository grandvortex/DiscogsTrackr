package com.grandvortex.discogstrackr.data.repository

import com.grandvortex.discogstrackr.data.Result
import com.grandvortex.discogstrackr.data.remote.dto.ArtistDTO
import com.grandvortex.discogstrackr.data.remote.dto.LabelDTO
import com.grandvortex.discogstrackr.data.remote.dto.ReleaseDTO
import com.grandvortex.discogstrackr.data.remote.retrofit.RemoteService
import com.grandvortex.discogstrackr.data.remote.safeRemoteCall
import com.grandvortex.discogstrackr.data.repository.parent.ResourceRepository
import javax.inject.Inject

class ResourceRepositoryDefault @Inject constructor(private val remoteService: RemoteService) :
    ResourceRepository {

    override suspend fun getArtist(id: Int): Result<ArtistDTO> {
        return safeRemoteCall { remoteService.getArtist(id) }
    }

    override suspend fun getLabel(id: Int): Result<LabelDTO> {
        return safeRemoteCall { remoteService.getLabel(id) }
    }

    override suspend fun getRelease(id: Int): Result<ReleaseDTO> {
        return safeRemoteCall { remoteService.getRelease(id) }
    }


}
