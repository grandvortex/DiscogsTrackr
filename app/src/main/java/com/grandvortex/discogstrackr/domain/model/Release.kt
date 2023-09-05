package com.grandvortex.discogstrackr.domain.model

data class Release(
    val artists: List<ReleaseArtist>,
    val artistsSort: String,
    val blockedFromSale: Boolean,
    val community: ReleaseCommunity,
    val companies: List<ReleaseSubinfo>,
    val country: String,
    val dataQuality: String,
    val dateAdded: String,
    val dateChanged: String,
    val estimatedWeight: Int,
    val extraartists: List<ReleaseArtist>,
    val formatQuantity: Int,
    val formats: List<ReleaseFormat>,
    val genres: List<String>,
    val id: Int,
    val identifiers: List<ReleaseIdentifier>,
    val images: List<ReleaseImage>,
    val labels: List<ReleaseSubinfo>,
    val lowestPrice: Double,
    val masterId: Int,
    val masterUrl: String,
    val notes: String,
    val numForSale: Int,
    val released: String,
    val releasedFormatted: String,
    val resourceUrl: String,
    val series: List<ReleaseSubinfo>,
    val status: String,
    val styles: List<String>,
    val thumb: String,
    val title: String,
    val tracklist: List<ReleaseTrack>,
    val uri: String,
    val videos: List<ReleaseVideo>,
    val year: Int
)

data class ReleaseArtist(
    val anv: String,
    val id: Int,
    val join: String,
    val name: String,
    val resourceUrl: String,
    val role: String,
    val tracks: String
)

data class ReleaseCommunity(
    val contributors: List<ReleaseContributor>,
    val dataQuality: String,
    val have: Int,
    val rating: ReleaseRating,
    val status: String,
    val submitter: ReleaseSubmitter,
    val want: Int
)

data class ReleaseContributor(
    val resourceUrl: String, val username: String
)

data class ReleaseRating(
    val average: Double, val count: Int
)

data class ReleaseSubmitter(
    val resourceUrl: String, val username: String
)

data class ReleaseSubinfo(
    val name: String,
    val catno: String,
    val entityType: String,
    val entityTypeName: String,
    val id: Int,
    val resourceUrl: String
)

data class ReleaseFormat(
    val descriptions: List<String>, val name: String, val qty: String
)

data class ReleaseIdentifier(
    val type: String, val value: String
)

data class ReleaseImage(
    val height: Int,
    val resourceUrl: String,
    val type: String,
    val uri: String,
    val uri150: String,
    val width: Int
)

data class ReleaseTrack(
    val duration: String,
    val extraartists: List<ReleaseArtist>,
    val artists: List<ReleaseArtist>,
    val position: String,
    val title: String,
    val type: String
)

data class ReleaseVideo(
    val description: String,
    val duration: Int,
    val embed: Boolean,
    val title: String,
    val uri: String
)