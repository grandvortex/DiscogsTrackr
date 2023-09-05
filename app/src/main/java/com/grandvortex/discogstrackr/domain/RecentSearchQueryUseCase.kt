package com.grandvortex.discogstrackr.domain

import com.grandvortex.discogstrackr.data.mapper.toRecentSearch
import com.grandvortex.discogstrackr.data.repository.parent.RecentSearchQueryRepository
import com.grandvortex.discogstrackr.domain.model.RecentSearch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecentSearchQueryUseCase @Inject constructor(
    private val recentSearchRepository: RecentSearchQueryRepository
) {

    fun getAllRecentSearchQueries(): Flow<List<RecentSearch>> {
        return recentSearchRepository.getAllRecentSearchQueries()
            .map { list -> list.map { entity -> entity.toRecentSearch() } }
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
