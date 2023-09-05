package com.grandvortex.discogstrackr.data.repository.parent

import com.grandvortex.discogstrackr.domain.model.Label
import com.grandvortex.discogstrackr.domain.model.Release
import com.grandvortex.discogstrackr.data.Result
import com.grandvortex.discogstrackr.data.remote.dto.ArtistDTO
import com.grandvortex.discogstrackr.data.remote.dto.LabelDTO
import com.grandvortex.discogstrackr.data.remote.dto.ReleaseDTO

interface ResourceRepository {
    suspend fun getArtist(id: Int): Result<ArtistDTO>
    suspend fun getLabel(id: Int): Result<LabelDTO>
    suspend fun getRelease(id: Int): Result<ReleaseDTO>
}
