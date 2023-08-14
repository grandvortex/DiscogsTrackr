package com.grandvortex.discogstrackr.di

import com.grandvortex.discogstrackr.data.local.repository.RecentSearchQueryRepository
import com.grandvortex.discogstrackr.data.remote.repository.ResourceRepository
import com.grandvortex.discogstrackr.data.remote.repository.SearchRepository
import com.grandvortex.discogstrackr.domain.ArtistUseCase
import com.grandvortex.discogstrackr.domain.RecentSearchQueryUseCase
import com.grandvortex.discogstrackr.domain.SearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideSearchUseCase(searchRepository: SearchRepository): SearchUseCase =
        SearchUseCase(searchRepository)

    @ViewModelScoped
    @Provides
    fun provideRecentSearchUseCase(recentSearchRepository: RecentSearchQueryRepository):
        RecentSearchQueryUseCase = RecentSearchQueryUseCase(recentSearchRepository)

    @ViewModelScoped
    @Provides
    fun provideArtistUseCase(resourceRepository: ResourceRepository):
        ArtistUseCase = ArtistUseCase(resourceRepository)
}
