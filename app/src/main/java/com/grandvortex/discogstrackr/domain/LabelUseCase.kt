package com.grandvortex.discogstrackr.domain

import com.grandvortex.discogstrackr.data.Result
import com.grandvortex.discogstrackr.data.mapper.toLabel
import com.grandvortex.discogstrackr.data.repository.parent.ResourceRepository
import com.grandvortex.discogstrackr.domain.model.Label
import javax.inject.Inject

class LabelUseCase @Inject constructor(private val resourceRepository: ResourceRepository) {

    suspend operator fun invoke(id: Int): Result<Label> {
        return when (val result = resourceRepository.getLabel(id)) {
            is Result.Success -> {
                Result.Success(result.data.toLabel())
            }

            is Result.Error -> {
                result
            }
        }
    }
}
