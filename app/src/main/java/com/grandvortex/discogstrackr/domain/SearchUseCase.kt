package com.grandvortex.discogstrackr.domain

import com.grandvortex.discogstrackr.data.ResourceType
import com.grandvortex.discogstrackr.data.Result
import com.grandvortex.discogstrackr.data.mapper.toSearchResults
import com.grandvortex.discogstrackr.data.repository.parent.SearchRepository
import com.grandvortex.discogstrackr.domain.model.SearchResults
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(query: String): Result<SearchResults> {
        return when (val remoteResult = searchRepository.search(query.trim())) {
            is Result.Success -> {
                val mappedResult = remoteResult.data.toSearchResults()
                val filteredList = mappedResult.results.filterNot { it.type == ResourceType.MASTER }
                val filteredResults = mappedResult.copy(results = filteredList)
                Result.Success(filteredResults)
            }

            is Result.Error -> {
                remoteResult
            }
        }
    }
}
