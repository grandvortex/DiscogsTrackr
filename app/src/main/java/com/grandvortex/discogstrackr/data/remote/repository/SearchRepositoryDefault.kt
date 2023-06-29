package com.grandvortex.discogstrackr.data.remote.repository

import com.grandvortex.discogstrackr.data.Result
import com.grandvortex.discogstrackr.data.mapper.toSearchResults
import com.grandvortex.discogstrackr.data.model.SearchResults
import com.grandvortex.discogstrackr.data.remote.retrofit.RemoteService
import javax.inject.Inject

class SearchRepositoryDefault @Inject constructor(private val remoteService: RemoteService) :
    SearchRepository {
    override suspend fun search(query: String): Result<SearchResults> {
        return safeNetworkCall { remoteService.search(query).toSearchResults() }
    }
}
