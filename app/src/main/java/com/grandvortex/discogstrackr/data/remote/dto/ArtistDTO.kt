package com.grandvortex.discogstrackr.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtistDTO(
    @Json(name = "aliases") val aliases: List<ArtistAliaseDTO>? = null,
    @Json(name = "data_quality") val dataQuality: String? = null,
    @Json(name = "id") val id: Int? = null,
    @Json(name = "images") val images: List<ArtistImageDTO>? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "namevariations") val namevariations: List<String>? = null,
    @Json(name = "profile") val profile: String? = null,
    @Json(name = "realname") val realname: String? = null,
    @Json(name = "releases_url") val releasesUrl: String? = null,
    @Json(name = "resource_url") val resourceUrl: String? = null,
    @Json(name = "uri") val uri: String? = null,
    @Json(name = "urls") val urls: List<String>? = null,
    @Json(name = "members") val members: List<ArtistMemberDTO>? = null
)

@JsonClass(generateAdapter = true)
data class ArtistMemberDTO(
    @Json(name = "active") val active: Boolean? = null,
    @Json(name = "id") val id: Int? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "resource_url") val resourceUrl: String? = null
)

@JsonClass(generateAdapter = true)
data class ArtistAliaseDTO(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "resource_url") val resourceUrl: String? = null
)

@JsonClass(generateAdapter = true)
data class ArtistImageDTO(
    @Json(name = "height") val height: Int? = null,
    @Json(name = "resource_url") val resourceUrl: String? = null,
    @Json(name = "type") val type: String? = null,
    @Json(name = "uri") val uri: String? = null,
    @Json(name = "uri150") val uri150: String? = null,
    @Json(name = "width") val width: Int? = null
)
