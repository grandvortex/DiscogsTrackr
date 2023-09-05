package com.grandvortex.discogstrackr.domain

import com.grandvortex.discogstrackr.data.Result
import com.grandvortex.discogstrackr.data.mapper.toRelease
import com.grandvortex.discogstrackr.data.repository.parent.ResourceRepository
import com.grandvortex.discogstrackr.domain.model.Release
import javax.inject.Inject

class ReleaseUseCase @Inject constructor(private val resourceRepository: ResourceRepository) {

    suspend operator fun invoke(id: Int): Result<Release> {
        return when (val result = resourceRepository.getRelease(id)) {
            is Result.Success -> {
                Result.Success(result.data.toRelease())
            }

            is Result.Error -> {
                result
            }
        }

    }
}