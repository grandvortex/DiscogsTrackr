package com.grandvortex.discogstrackr.di

import com.grandvortex.discogstrackr.data.local.dao.RecentSearchQueryDao
import com.grandvortex.discogstrackr.data.local.repository.RecentSearchQueryRepository
import com.grandvortex.discogstrackr.data.local.repository.RecentSearchQueryRepositoryDefault
import com.grandvortex.discogstrackr.data.remote.repository.ResourceRepository
import com.grandvortex.discogstrackr.data.remote.repository.ResourceRepositoryDefault
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
    fun provideRecentSearchQueryRepository(
        recentSearchDao: RecentSearchQueryDao,
        @ApplicationScope appCoroutineScope: CoroutineScope
    ): RecentSearchQueryRepository =
        RecentSearchQueryRepositoryDefault(recentSearchDao, appCoroutineScope)

    @Singleton
    @Provides
    fun provideArtistRepository(remoteService: RemoteService): ResourceRepository =
        ResourceRepositoryDefault(remoteService)
}
