package com.grandvortex.discogstrackr.domain.model

data class SearchResults(
    val pagination: Pagination,
    val results: List<SearchResult>
)

data class Pagination(
    val items: Int,
    val page: Int,
    val pages: Int,
    val per_page: Int,
    val urls: Urls
)

data class Urls(
    val last: String,
    val next: String
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
    val cover_image: String,
    val format: List<String>,
    val format_quantity: Int,
    val formats: List<Format>,
    val genre: List<String>,
    val label: List<String>,
    val master_id: Int,
    val master_url: String,
    val resource_url: String,
    val style: List<String>,
    val thumb: String,
    val title: String,
    val type: String,
    val uri: String,
    val year: String
)
