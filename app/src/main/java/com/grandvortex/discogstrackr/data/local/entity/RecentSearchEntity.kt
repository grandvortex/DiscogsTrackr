package com.grandvortex.discogstrackr.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant

@Entity(tableName = "recentSearchQueries")
data class RecentSearchEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "queryText") val queryText: String,
    @ColumnInfo(name = "quriedDate") val queriedDate: Instant
)
