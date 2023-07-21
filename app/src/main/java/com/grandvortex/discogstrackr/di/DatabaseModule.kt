package com.grandvortex.discogstrackr.di

import android.content.Context
import androidx.room.Room
import com.grandvortex.discogstrackr.data.local.AppDatabase
import com.grandvortex.discogstrackr.data.local.dao.RecentSearchQueryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val DATABASE_NAME = "discogs_trackr_db"

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    fun provideRecentSearchDao(
        database: AppDatabase
    ): RecentSearchQueryDao = database.recentSearchDao()
}
