package com.grandvortex.discogstrackr.di

import com.grandvortex.discogstrackr.data.local.repository.RecentSearchQueryRepository
import com.grandvortex.discogstrackr.data.local.repository.RecentSearchQueryRepositoryDefault
import com.grandvortex.discogstrackr.data.remote.repository.ResourceRepository
import com.grandvortex.discogstrackr.data.remote.repository.ResourceRepositoryDefault
import com.grandvortex.discogstrackr.data.remote.repository.SearchRepository
import com.grandvortex.discogstrackr.data.remote.repository.SearchRepositoryDefault
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindSearchRepository(repo: SearchRepositoryDefault): SearchRepository

    @Binds
    @ViewModelScoped
    abstract fun bindRecentSearchQueryRepository(repo: RecentSearchQueryRepositoryDefault): RecentSearchQueryRepository

    @Binds
    @ViewModelScoped
    abstract fun bindResourceRepository(repo: ResourceRepositoryDefault): ResourceRepository
}
