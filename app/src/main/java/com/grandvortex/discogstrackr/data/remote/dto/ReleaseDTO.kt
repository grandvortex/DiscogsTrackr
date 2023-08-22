package com.grandvortex.discogstrackr.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReleaseDTO(
    @Json(name = "artists") val artists: List<ReleaseArtistDTO>? = null,
    @Json(name = "artists_sort") val artistsSort: String? = null,
    @Json(name = "blocked_from_sale") val blockedFromSale: Boolean? = null,
    @Json(name = "community") val community: ReleaseCommunityDTO? = null,
    @Json(name = "companies") val companies: List<ReleaseSubinfoDTO>? = null,
    @Json(name = "country") val country: String? = null,
    @Json(name = "data_quality") val dataQuality: String? = null,
    @Json(name = "date_added") val dateAdded: String? = null,
    @Json(name = "date_changed") val dateChanged: String? = null,
    @Json(name = "estimated_weight") val estimatedWeight: Int? = null,
    @Json(name = "extraartists") val extraartists: List<ReleaseArtistDTO>? = null,
    @Json(name = "format_quantity") val formatQuantity: Int? = null,
    @Json(name = "formats") val formats: List<ReleaseFormatDTO>? = null,
    @Json(name = "genres") val genres: List<String>? = null,
    @Json(name = "id") val id: Int? = null,
    @Json(name = "identifiers") val identifiers: List<ReleaseIdentifierDTO>? = null,
    @Json(name = "images") val images: List<ReleaseImageDTO>? = null,
    @Json(name = "labels") val labels: List<ReleaseSubinfoDTO>? = null,
    @Json(name = "lowest_price") val lowestPrice: Double? = null,
    @Json(name = "master_id") val masterId: Int? = null,
    @Json(name = "master_url") val masterUrl: String? = null,
    @Json(name = "notes") val notes: String? = null,
    @Json(name = "num_for_sale") val numForSale: Int? = null,
    @Json(name = "released") val released: String? = null,
    @Json(name = "released_formatted") val releasedFormatted: String? = null,
    @Json(name = "resource_url") val resourceUrl: String? = null,
    @Json(name = "series") val series: List<ReleaseSubinfoDTO>? = null,
    @Json(name = "status") val status: String? = null,
    @Json(name = "styles") val styles: List<String>? = null,
    @Json(name = "thumb") val thumb: String? = null,
    @Json(name = "title") val title: String? = null,
    @Json(name = "tracklist") val tracklist: List<ReleaseTrackDTO>? = null,
    @Json(name = "uri") val uri: String? = null,
    @Json(name = "videos") val videos: List<ReleaseVideoDTO>? = null,
    @Json(name = "year") val year: Int? = null
)

@JsonClass(generateAdapter = true)
data class ReleaseArtistDTO(
    @Json(name = "anv") val anv: String? = null,
    @Json(name = "id") val id: Int? = null,
    @Json(name = "join") val join: String? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "resource_url") val resourceUrl: String? = null,
    @Json(name = "role") val role: String? = null,
    @Json(name = "tracks") val tracks: String? = null
)

@JsonClass(generateAdapter = true)
data class ReleaseCommunityDTO(
    @Json(name = "contributors") val contributors: List<ReleaseContributorDTO>? = null,
    @Json(name = "data_quality") val dataQuality: String? = null,
    @Json(name = "have") val have: Int? = null,
    @Json(name = "rating") val rating: ReleaseRatingDTO? = null,
    @Json(name = "status") val status: String? = null,
    @Json(name = "submitter") val submitter: ReleaseSubmitterDTO? = null,
    @Json(name = "want") val want: Int? = null
)

@JsonClass(generateAdapter = true)
data class ReleaseContributorDTO(
    @Json(name = "resource_url") val resourceUrl: String? = null,
    @Json(name = "username") val username: String? = null
)

@JsonClass(generateAdapter = true)
data class ReleaseRatingDTO(
    @Json(name = "average") val average: Double? = null,
    @Json(name = "count") val count: Int? = null
)

@JsonClass(generateAdapter = true)
data class ReleaseSubmitterDTO(
    @Json(name = "resource_url") val resourceUrl: String? = null,
    @Json(name = "username") val username: String? = null
)

@JsonClass(generateAdapter = true)
data class ReleaseFormatDTO(
    @Json(name = "descriptions") val descriptions: List<String>? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "qty") val qty: String? = null
)

@JsonClass(generateAdapter = true)
data class ReleaseIdentifierDTO(
    @Json(name = "type") val type: String? = null, @Json(name = "value") val value: String? = null
)

@JsonClass(generateAdapter = true)
data class ReleaseImageDTO(
    @Json(name = "height") val height: Int? = null,
    @Json(name = "resource_url") val resourceUrl: String? = null,
    @Json(name = "type") val type: String? = null,
    @Json(name = "uri") val uri: String? = null,
    @Json(name = "uri150") val uri150: String? = null,
    @Json(name = "width") val width: Int? = null
)

@JsonClass(generateAdapter = true)
data class ReleaseSubinfoDTO(
    @Json(name = "name") val name: String? = null,
    @Json(name = "catno") val catno: String? = null,
    @Json(name = "entity_type") val entityType: String? = null,
    @Json(name = "entity_type_name") val entityTypeName: String? = null,
    @Json(name = "id") val id: Int? = null,
    @Json(name = "resource_url") val resourceUrl: String? = null
)

@JsonClass(generateAdapter = true)
data class ReleaseTrackDTO(
    @Json(name = "duration") val duration: String? = null,
    @Json(name = "artists") val artists: List<ReleaseArtistDTO>? = null,
    @Json(name = "extraartists") val extraartists: List<ReleaseArtistDTO>? = null,
    @Json(name = "position") val position: String? = null,
    @Json(name = "title") val title: String? = null,
    @Json(name = "type_") val type: String? = null
)

@JsonClass(generateAdapter = true)
data class ReleaseVideoDTO(
    @Json(name = "description") val description: String? = null,
    @Json(name = "duration") val duration: Int? = null,
    @Json(name = "embed") val embed: Boolean? = null,
    @Json(name = "title") val title: String? = null,
    @Json(name = "uri") val uri: String? = null
)