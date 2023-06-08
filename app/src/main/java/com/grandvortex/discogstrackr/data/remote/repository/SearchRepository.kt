package com.grandvortex.discogstrackr.data.remote.repository

import com.grandvortex.discogstrackr.domain.model.SearchResults
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun search(query: String): Flow<SearchResults>
}
