package com.grandvortex.discogstrackr.application.di

import com.grandvortex.discogstrackr.data.repository.RecentSearchQueryRepositoryDefault
import com.grandvortex.discogstrackr.data.repository.ResourceRepositoryDefault
import com.grandvortex.discogstrackr.data.repository.SearchRepositoryDefault
import com.grandvortex.discogstrackr.data.repository.parent.RecentSearchQueryRepository
import com.grandvortex.discogstrackr.data.repository.parent.ResourceRepository
import com.grandvortex.discogstrackr.data.repository.parent.SearchRepository
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
