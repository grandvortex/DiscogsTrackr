package com.grandvortex.discogstrackr.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.grandvortex.discogstrackr.data.local.dao.RecentSearchDao
import com.grandvortex.discogstrackr.data.local.entity.RecentSearchEntity

@Database(entities = [RecentSearchEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recentSearchDao(): RecentSearchDao
}
