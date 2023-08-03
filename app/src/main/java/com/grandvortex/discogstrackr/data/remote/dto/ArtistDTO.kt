package com.grandvortex.discogstrackr.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtistDTO(
    @Json(name = "aliases") val aliases: List<AliaseDTO>?,
    @Json(name = "data_quality") val dataQuality: String?,
    @Json(name = "id") val id: Int?,
    @Json(name = "images") val images: List<ImageDTO>?,
    @Json(name = "name") val name: String?,
    @Json(name = "namevariations") val namevariations: List<String>?,
    @Json(name = "profile") val profile: String?,
    @Json(name = "realname") val realname: String?,
    @Json(name = "releases_url") val releasesUrl: String?,
    @Json(name = "resource_url") val resourceUrl: String?,
    @Json(name = "uri") val uri: String?,
    @Json(name = "urls") val urls: List<String>?
)

@JsonClass(generateAdapter = true)
data class AliaseDTO(
    @Json(name = "id") val id: Int?,
    @Json(name = "name") val name: String?,
    @Json(name = "resource_url") val resourceUrl: String?
)

@JsonClass(generateAdapter = true)
data class ImageDTO(
    @Json(name = "height") val height: Int?,
    @Json(name = "resource_url") val resourceUrl: String?,
    @Json(name = "type") val type: String?,
    @Json(name = "uri") val uri: String?,
    @Json(name = "uri150") val uri150: String?,
    @Json(name = "width") val width: Int?
)
