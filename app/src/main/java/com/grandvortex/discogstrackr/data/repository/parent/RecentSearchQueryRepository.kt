package com.grandvortex.discogstrackr.data.repository.parent

import com.grandvortex.discogstrackr.data.local.entity.RecentSearchEntity
import com.grandvortex.discogstrackr.domain.model.RecentSearch
import kotlinx.coroutines.flow.Flow

interface RecentSearchQueryRepository {
    fun getAllRecentSearchQueries(): Flow<List<RecentSearchEntity>>

    suspend fun upsertSearchQuery(query: String)

    suspend fun deleteSearchQuery(query: String)

    suspend fun clearRecentSearchQueries()
}
