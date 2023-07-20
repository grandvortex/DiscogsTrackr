package com.grandvortex.discogstrackr.di

import com.grandvortex.discogstrackr.data.local.dao.RecentSearchDao
import com.grandvortex.discogstrackr.data.local.repository.RecentSearchRepository
import com.grandvortex.discogstrackr.data.local.repository.RecentSearchRepositoryDefault
import com.grandvortex.discogstrackr.data.remote.repository.SearchRepository
import com.grandvortex.discogstrackr.data.remote.repository.SearchRepositoryDefault
import com.grandvortex.discogstrackr.data.remote.retrofit.RemoteService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideSearchRepository(remoteService: RemoteService): SearchRepository =
        SearchRepositoryDefault(remoteService)

    @Singleton
    @Provides
    fun provideRecentSearchRepository(
        recentSearchDao: RecentSearchDao,
        @ApplicationScope appCoroutineScope: CoroutineScope
    ): RecentSearchRepository = RecentSearchRepositoryDefault(recentSearchDao, appCoroutineScope)
}
