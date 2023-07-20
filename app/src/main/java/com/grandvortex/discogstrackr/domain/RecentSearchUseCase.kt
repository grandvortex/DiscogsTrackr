package com.grandvortex.discogstrackr.domain

import com.grandvortex.discogstrackr.data.local.repository.RecentSearchRepository
import com.grandvortex.discogstrackr.data.model.RecentSearch
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class RecentSearchUseCase @Inject constructor(
    private val recentSearchRepository: RecentSearchRepository
) {

    fun getAllRecentSearchQueries(): Flow<List<RecentSearch>> {
        return recentSearchRepository.getAllRecentSearchQueries()
    }

    suspend fun upsertSearchQuery(query: String) {
        recentSearchRepository.upsertSearchQuery(query)
    }

    suspend fun deleteSearchQuery(query: String) {
        recentSearchRepository.deleteSearchQuery(query)
    }

    suspend fun clearRecentSearchQueries() {
        recentSearchRepository.clearRecentSearchQueries()
    }
}
