package com.grandvortex.discogstrackr.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.grandvortex.discogstrackr.data.local.entity.RecentSearchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentSearchQueryDao {

    @Query("SELECT * FROM recentSearchQueries ORDER BY quriedDate DESC")
    fun getAllRecentSearchQueries(): Flow<List<RecentSearchEntity>>

    @Upsert
    suspend fun upsertRecentSearchQuery(query: RecentSearchEntity)

    @Query("DELETE FROM recentSearchQueries WHERE queryText = :query")
    suspend fun deleteRecentSearchQuery(query: String)

    @Query("DELETE FROM recentSearchQueries")
    suspend fun clearRecentSearchQueries()
}
