package com.grandvortex.discogstrackr.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResultsDTO(
    @Json(name = "pagination") val pagination: SearchResultsPaginationDTO? = null,
    @Json(name = "results") val results: List<SearchResultDTO>
)

@JsonClass(generateAdapter = true)
data class SearchResultsPaginationDTO(
    @Json(name = "items") val items: Int? = null,
    @Json(name = "page") val page: Int? = null,
    @Json(name = "pages") val pages: Int? = null,
    @Json(name = "per_page") val perPage: Int? = null,
    @Json(name = "urls") val urls: SearchResultsUrlsDTO? = null
)

@JsonClass(generateAdapter = true)
data class SearchResultsUrlsDTO(
    @Json(name = "last") val last: String? = null,
    @Json(name = "next") val next: String? = null,
    @Json(name = "prev") val prev: String? = null,
    @Json(name = "first") val first: String? = null
)

@JsonClass(generateAdapter = true)
data class CommunityDTO(
    @Json(name = "have") val have: Int? = null,
    @Json(name = "want") val want: Int? = null
)

@JsonClass(generateAdapter = true)
data class SearchResultsFormatDTO(
    @Json(name = "descriptions") val descriptions: List<String>? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "qty") val qty: String? = null,
    @Json(name = "text") val text: String? = null
)

@JsonClass(generateAdapter = true)
data class SearchResultDTO(
    @Json(name = "barcode") val barcode: List<String>? = null,
    @Json(name = "catno") val catno: String? = null,
    @Json(name = "community") val community: CommunityDTO? = null,
    @Json(name = "country") val country: String? = null,
    @Json(name = "cover_image") val coverImage: String? = null,
    @Json(name = "format") val format: List<String>? = null,
    @Json(name = "format_quantity") val formatQuantity: Int? = null,
    @Json(name = "formats") val formats: List<SearchResultsFormatDTO>? = null,
    @Json(name = "genre") val genre: List<String>? = null,
    @Json(name = "id") val id: Int? = null,
    @Json(name = "label") val label: List<String>? = null,
    @Json(name = "master_id") val masterId: Int? = null,
    @Json(name = "master_url") val masterUrl: String? = null,
    @Json(name = "resource_url") val resourceUrl: String? = null,
    @Json(name = "style") val style: List<String>? = null,
    @Json(name = "thumb") val thumb: String? = null,
    @Json(name = "title") val title: String? = null,
    @Json(name = "type") val type: String? = null,
    @Json(name = "uri") val uri: String? = null,
    @Json(name = "year") val year: String? = null
)
