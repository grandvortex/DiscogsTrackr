package com.grandvortex.discogstrackr.domain

import com.grandvortex.discogstrackr.data.model.Label
import com.grandvortex.discogstrackr.data.remote.RemoteResult
import com.grandvortex.discogstrackr.data.repository.parent.ResourceRepository
import javax.inject.Inject

class LabelUseCase @Inject constructor(private val resourceRepository: ResourceRepository) {

    suspend operator fun invoke(id: Int): RemoteResult<Label> {
        return resourceRepository.getLabel(id)
    }
}
