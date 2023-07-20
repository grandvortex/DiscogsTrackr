package com.grandvortex.discogstrackr.di

import com.grandvortex.discogstrackr.data.local.repository.RecentSearchRepository
import com.grandvortex.discogstrackr.data.remote.repository.SearchRepository
import com.grandvortex.discogstrackr.domain.RecentSearchUseCase
import com.grandvortex.discogstrackr.domain.SearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideSearchUseCase(searchRepository: SearchRepository): SearchUseCase =
        SearchUseCase(searchRepository)

    @Singleton
    @Provides
    fun provideRecentSearchUseCase(recentSearchRepository: RecentSearchRepository):
        RecentSearchUseCase = RecentSearchUseCase(recentSearchRepository)
}
