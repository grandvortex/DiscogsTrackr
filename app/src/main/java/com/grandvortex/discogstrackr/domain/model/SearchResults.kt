package com.grandvortex.discogstrackr.domain.model

import com.grandvortex.discogstrackr.data.ResourceType

data class SearchResults(
    val pagination: Pagination,
    val results: List<SearchResult>
)

data class Pagination(
    val items: Int,
    val page: Int,
    val pages: Int,
    val perPage: Int,
    val urls: Urls
)

data class Urls(
    val last: String,
    val next: String,
    val prev: String,
    val first: String
)

data class Community(
    val have: Int,
    val want: Int
)

data class Format(
    val descriptions: List<String>,
    val name: String,
    val qty: String,
    val text: String
)

data class SearchResult(
    val id: Int,
    val barcode: List<String>,
    val catno: String,
    val community: Community,
    val country: String,
    val coverImage: String,
    val format: List<String>,
    val formatQuantity: Int,
    val formats: List<Format>,
    val genre: List<String>,
    val label: List<String>,
    val masterId: Int,
    val masterUrl: String,
    val resourceUrl: String,
    val style: List<String>,
    val thumb: String,
    val title: String,
    val type: ResourceType,
    val uri: String,
    val year: String
)
