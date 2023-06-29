package com.grandvortex.discogstrackr.data.remote.repository

import com.grandvortex.discogstrackr.data.Result
import com.grandvortex.discogstrackr.data.model.SearchResults

interface SearchRepository {
    suspend fun search(query: String): Result<SearchResults>
}
