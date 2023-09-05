package com.grandvortex.discogstrackr.data.repository

import com.grandvortex.discogstrackr.data.Result
import com.grandvortex.discogstrackr.data.remote.dto.SearchResultsDTO
import com.grandvortex.discogstrackr.data.remote.retrofit.RemoteService
import com.grandvortex.discogstrackr.data.remote.safeRemoteCall
import com.grandvortex.discogstrackr.data.repository.parent.SearchRepository
import javax.inject.Inject

class SearchRepositoryDefault @Inject constructor(private val remoteService: RemoteService) :
    SearchRepository {
    override suspend fun search(query: String): Result<SearchResultsDTO> {
        return safeRemoteCall { remoteService.search(query) }
    }
}
