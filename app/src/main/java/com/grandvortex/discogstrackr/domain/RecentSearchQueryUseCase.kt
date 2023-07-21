package com.grandvortex.discogstrackr.domain

import com.grandvortex.discogstrackr.data.local.repository.RecentSearchQueryRepository
import com.grandvortex.discogstrackr.data.model.RecentSearch
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class RecentSearchQueryUseCase @Inject constructor(
    private val recentSearchRepository: RecentSearchQueryRepository
) {

    fun getAllRecentSearchQueries(): Flow<List<RecentSearch>> {
        return recentSearchRepository.getAllRecentSearchQueries()
    }

    suspend fun upsertSearchQuery(query: String) {
        recentSearchRepository.upsertSearchQuery(query.trim())
    }

    suspend fun deleteSearchQuery(query: String) {
        recentSearchRepository.deleteSearchQuery(query.trim())
    }

    suspend fun clearRecentSearchQueries() {
        recentSearchRepository.clearRecentSearchQueries()
    }
}
