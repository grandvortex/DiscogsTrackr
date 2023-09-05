package com.grandvortex.discogstrackr.domain

import com.grandvortex.discogstrackr.data.Result
import com.grandvortex.discogstrackr.data.mapper.toArtist
import com.grandvortex.discogstrackr.data.repository.parent.ResourceRepository
import com.grandvortex.discogstrackr.domain.model.Artist
import javax.inject.Inject

class ArtistUseCase @Inject constructor(private val resourceRepository: ResourceRepository) {

    suspend operator fun invoke(id: Int): Result<Artist> {
        return when (val result = resourceRepository.getArtist(id)) {
            is Result.Success -> {
                Result.Success(result.data.toArtist())
            }

            is Result.Error -> {
                result
            }
        }
    }
}
