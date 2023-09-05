package com.grandvortex.discogstrackr.data.repository.parent

import com.grandvortex.discogstrackr.domain.model.SearchResults
import com.grandvortex.discogstrackr.data.Result
import com.grandvortex.discogstrackr.data.remote.dto.SearchResultsDTO

interface SearchRepository {
    suspend fun search(query: String): Result<SearchResultsDTO>
}
