package com.grandvortex.discogstrackr.data.remote.repository

import com.grandvortex.discogstrackr.data.remote.retrofit.RemoteService
import com.grandvortex.discogstrackr.domain.model.SearchResults
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class SearchContentRepository @Inject constructor(remote: RemoteService) : SearchRepository {
    override fun search(query: String): Flow<SearchResults> {
        TODO("Not yet implemented")
    }
}
