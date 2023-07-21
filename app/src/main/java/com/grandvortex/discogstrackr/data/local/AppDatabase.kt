package com.grandvortex.discogstrackr.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.grandvortex.discogstrackr.data.local.converter.InstantConverter
import com.grandvortex.discogstrackr.data.local.dao.RecentSearchQueryDao
import com.grandvortex.discogstrackr.data.local.entity.RecentSearchEntity

@Database(entities = [RecentSearchEntity::class], version = 1, exportSchema = true)
@TypeConverters(InstantConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recentSearchDao(): RecentSearchQueryDao
}
