package com.grandvortex.discogstrackr.domain

import com.grandvortex.discogstrackr.data.model.RecentSearch
import com.grandvortex.discogstrackr.data.repository.parent.RecentSearchQueryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

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
