package com.grandvortex.discogstrackr.data.repository

import com.grandvortex.discogstrackr.application.di.ApplicationScope
import com.grandvortex.discogstrackr.data.local.dao.RecentSearchQueryDao
import com.grandvortex.discogstrackr.data.local.entity.RecentSearchEntity
import com.grandvortex.discogstrackr.data.repository.parent.RecentSearchQueryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import javax.inject.Inject

class RecentSearchQueryRepositoryDefault @Inject constructor(
    private val recentSearchDao: RecentSearchQueryDao,
    @ApplicationScope private val appCoroutineScope: CoroutineScope
) : RecentSearchQueryRepository {

    override fun getAllRecentSearchQueries(): Flow<List<RecentSearchEntity>> {
        return recentSearchDao.getAllRecentSearchQueries()
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
