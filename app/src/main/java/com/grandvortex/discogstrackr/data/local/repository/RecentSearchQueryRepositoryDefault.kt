package com.grandvortex.discogstrackr.data.local.repository

import com.grandvortex.discogstrackr.data.local.dao.RecentSearchQueryDao
import com.grandvortex.discogstrackr.data.local.entity.RecentSearchEntity
import com.grandvortex.discogstrackr.data.mapper.toRecentSearch
import com.grandvortex.discogstrackr.data.model.RecentSearch
import com.grandvortex.discogstrackr.di.ApplicationScope
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock

class RecentSearchQueryRepositoryDefault @Inject constructor(
    private val recentSearchDao: RecentSearchQueryDao,
    @ApplicationScope private val appCoroutineScope: CoroutineScope
) : RecentSearchQueryRepository {

    override fun getAllRecentSearchQueries(): Flow<List<RecentSearch>> {
        return recentSearchDao.getAllRecentSearchQueries()
            .map { list -> list.map { entity -> entity.toRecentSearch() } }
    }

    override suspend fun upsertSearchQuery(query: String) {
        withContext(appCoroutineScope.coroutineContext) {
            recentSearchDao.upsertRecentSearchQuery(
                RecentSearchEntity(queryText = query, queriedDate = Clock.System.now())
            )
        }
    }

    override suspend fun deleteSearchQuery(query: String) {
        withContext(appCoroutineScope.coroutineContext) {
            recentSearchDao.deleteRecentSearchQuery(query)
        }
    }

    override suspend fun clearRecentSearchQueries() {
        withContext(appCoroutineScope.coroutineContext) {
            recentSearchDao.clearRecentSearchQueries()
        }
    }
}
