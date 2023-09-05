package com.grandvortex.discogstrackr.data.mapper

import com.grandvortex.discogstrackr.data.local.entity.RecentSearchEntity
import com.grandvortex.discogstrackr.domain.model.RecentSearch

fun RecentSearchEntity.toRecentSearch() = RecentSearch(queryText, queriedDate)
