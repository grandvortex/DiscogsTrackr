package com.grandvortex.discogstrackr.data.local.repository

import com.grandvortex.discogstrackr.data.model.RecentSearch
import kotlinx.coroutines.flow.Flow

interface RecentSearchQueryRepository {
    fun getAllRecentSearchQueries(): Flow<List<RecentSearch>>

    suspend fun upsertSearchQuery(query: String)

    suspend fun deleteSearchQuery(query: String)

    suspend fun clearRecentSearchQueries()
}
