package com.grandvortex.discogstrackr.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResultsRemote(
    @Json(name = "pagination") val pagination: Pagination,
    @Json(name = "results") val results: List<SearchResult>
)

@JsonClass(generateAdapter = true)
data class Pagination(
    @Json(name = "items") val items: Int,
    @Json(name = "page") val page: Int,
    @Json(name = "pages") val pages: Int,
    @Json(name = "per_page") val perPage: Int,
    @Json(name = "urls") val urls: Urls
)

@JsonClass(generateAdapter = true)
data class Urls(
    @Json(name = "last") val last: String,
    @Json(name = "next") val next: String,
    @Json(name = "prev") val prev: String,
    @Json(name = "first") val first: String
)

@JsonClass(generateAdapter = true)
data class Community(
    @Json(name = "have") val have: Int,
    @Json(name = "want") val want: Int
)

@JsonClass(generateAdapter = true)
data class Format(
    @Json(name = "descriptions") val descriptions: List<String>,
    @Json(name = "name") val name: String,
    @Json(name = "qty") val qty: String,
    @Json(name = "text") val text: String
)

@JsonClass(generateAdapter = true)
data class SearchResult(
    @Json(name = "barcode") val barcode: List<String>,
    @Json(name = "catno") val catno: String,
    @Json(name = "community") val community: Community,
    @Json(name = "country") val country: String,
    @Json(name = "cover_image") val coverImage: String,
    @Json(name = "format") val format: List<String>,
    @Json(name = "format_quantity") val formatQuantity: Int,
    @Json(name = "formats") val formats: List<Format>,
    @Json(name = "genre") val genre: List<String>,
    @Json(name = "id") val id: Int,
    @Json(name = "label") val label: List<String>,
    @Json(name = "master_id") val masterId: Int,
    @Json(name = "master_url") val masterUrl: String,
    @Json(name = "resource_url") val resourceUrl: String,
    @Json(name = "style") val style: List<String>,
    @Json(name = "thumb") val thumb: String,
    @Json(name = "title") val title: String,
    @Json(name = "type") val type: String,
    @Json(name = "uri") val uri: String,
    @Json(name = "year") val year: String
)
