package com.grandvortex.discogstrackr.data.repository.parent

import com.grandvortex.discogstrackr.data.model.Artist
import com.grandvortex.discogstrackr.data.model.Label
import com.grandvortex.discogstrackr.data.model.Release
import com.grandvortex.discogstrackr.data.remote.RemoteResult

interface ResourceRepository {
    suspend fun getArtist(id: Int): RemoteResult<Artist>
    suspend fun getLabel(id: Int): RemoteResult<Label>
    suspend fun getRelease(id: Int): RemoteResult<Release>
}
