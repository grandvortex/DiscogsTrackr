package com.grandvortex.discogstrackr.domain

import com.grandvortex.discogstrackr.data.model.Artist
import com.grandvortex.discogstrackr.data.remote.RemoteResult
import com.grandvortex.discogstrackr.data.repository.parent.ResourceRepository
import javax.inject.Inject

class ArtistUseCase @Inject constructor(private val resourceRepository: ResourceRepository) {

    suspend operator fun invoke(id: Int): RemoteResult<Artist> {
        return resourceRepository.getArtist(id)
    }
}
