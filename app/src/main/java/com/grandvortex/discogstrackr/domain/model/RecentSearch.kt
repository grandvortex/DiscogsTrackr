package com.grandvortex.discogstrackr.domain.model

import kotlinx.datetime.Instant

data class RecentSearch(
    val queryText: String,
    val queriedDate: Instant
)
