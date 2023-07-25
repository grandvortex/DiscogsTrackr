package com.grandvortex.discogstrackr.data.remote.repository

import com.grandvortex.discogstrackr.data.model.SearchResults
import com.grandvortex.discogstrackr.data.remote.RemoteResult

interface SearchRepository {
    suspend fun search(query: String): RemoteResult<SearchResults>
}
