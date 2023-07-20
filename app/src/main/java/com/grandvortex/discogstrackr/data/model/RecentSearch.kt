package com.grandvortex.discogstrackr.data.model

import kotlinx.datetime.Instant

data class RecentSearch(
    val queryText: String,
    val queriedDate: Instant
)
