package com.grandvortex.discogstrackr.domain

import com.grandvortex.discogstrackr.data.model.Release
import com.grandvortex.discogstrackr.data.remote.RemoteResult
import com.grandvortex.discogstrackr.data.repository.parent.ResourceRepository
import javax.inject.Inject

class ReleaseUseCase @Inject constructor(private val resourceRepository: ResourceRepository) {

    suspend operator fun invoke(id: Int): RemoteResult<Release> {
        return resourceRepository.getRelease(id)
    }
}