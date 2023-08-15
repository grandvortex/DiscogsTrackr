package com.grandvortex.discogstrackr.domain

import com.grandvortex.discogstrackr.data.model.Label
import com.grandvortex.discogstrackr.data.remote.RemoteResult
import com.grandvortex.discogstrackr.data.remote.repository.ResourceRepository
import javax.inject.Inject

class LabelUseCase @Inject constructor(private val resourceRepository: ResourceRepository) {

    suspend fun getLabel(id: Int): RemoteResult<Label> {
        return resourceRepository.getLabel(id)
    }
}
