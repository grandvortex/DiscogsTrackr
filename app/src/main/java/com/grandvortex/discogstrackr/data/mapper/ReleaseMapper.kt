package com.grandvortex.discogstrackr.data.mapper

import com.grandvortex.discogstrackr.data.remote.dto.ReleaseArtistDTO
import com.grandvortex.discogstrackr.data.remote.dto.ReleaseDTO
import com.grandvortex.discogstrackr.data.remote.dto.ReleaseSubinfoDTO
import com.grandvortex.discogstrackr.domain.model.Release
import com.grandvortex.discogstrackr.domain.model.ReleaseArtist
import com.grandvortex.discogstrackr.domain.model.ReleaseCommunity
import com.grandvortex.discogstrackr.domain.model.ReleaseContributor
import com.grandvortex.discogstrackr.domain.model.ReleaseFormat
import com.grandvortex.discogstrackr.domain.model.ReleaseIdentifier
import com.grandvortex.discogstrackr.domain.model.ReleaseImage
import com.grandvortex.discogstrackr.domain.model.ReleaseRating
import com.grandvortex.discogstrackr.domain.model.ReleaseSubinfo
import com.grandvortex.discogstrackr.domain.model.ReleaseSubmitter
import com.grandvortex.discogstrackr.domain.model.ReleaseTrack
import com.grandvortex.discogstrackr.domain.model.ReleaseVideo

fun ReleaseDTO.toRelease(): Release {
    val releaseContributors = community?.contributors?.map { dto ->
        ReleaseContributor(
            resourceUrl = dto.resourceUrl ?: "", username = dto.username ?: ""
        )
    } ?: emptyList()
    val releaseRating = ReleaseRating(
        average = community?.rating?.average ?: 0.0, count = community?.rating?.count ?: 0
    )
    val releaseSubmitter = ReleaseSubmitter(
        resourceUrl = community?.submitter?.resourceUrl ?: "",
        username = community?.submitter?.username ?: ""
    )
    val releaseCommunity = ReleaseCommunity(
        contributors = releaseContributors,
        dataQuality = community?.dataQuality ?: "",
        have = community?.have ?: 0,
        rating = releaseRating,
        status = community?.status ?: "",
        submitter = releaseSubmitter,
        want = community?.want ?: 0
    )

    val releaseFormats = formats?.map { dto ->
        ReleaseFormat(
            descriptions = dto.descriptions ?: emptyList(),
            name = dto.name ?: "",
            qty = dto.qty ?: ""
        )
    } ?: emptyList()

    val releaseIdentifiers = identifiers?.map { dto ->
        ReleaseIdentifier(type = dto.type ?: "", value = dto.value ?: "")
    } ?: emptyList()

    val releaseImages = images?.map { dto ->
        ReleaseImage(
            height = dto.height ?: 0,
            resourceUrl = dto.resourceUrl ?: "",
            type = dto.type ?: "",
            uri = dto.uri ?: "",
            uri150 = dto.uri150 ?: "",
            width = dto.width ?: 0
        )
    } ?: emptyList()

    val releaseTrackList = tracklist?.filter { dto -> dto.type.equals("track", true) }?.map { dto ->
        ReleaseTrack(
            duration = dto.duration ?: "",
            position = dto.position ?: "",
            title = dto.title ?: "",
            type = dto.type ?: "",
            extraartists = dto.extraartists.toReleaseArtist(),
            artists = dto.artists.toReleaseArtist()
        )
    } ?: emptyList()

    val releaseVideos = videos?.map { dto ->
        ReleaseVideo(
            description = dto.description ?: "",
            duration = dto.duration ?: 0,
            embed = dto.embed ?: false,
            title = dto.title ?: "",
            uri = dto.uri ?: ""
        )
    } ?: emptyList()

    return Release(
        artists = artists.toReleaseArtist(),
        artistsSort = artistsSort ?: "",
        blockedFromSale = blockedFromSale ?: true,
        community = releaseCommunity,
        companies = companies.toReleaseSubinfo(),
        country = country ?: "",
        dataQuality = dataQuality ?: "",
        dateAdded = dateAdded ?: "",
        dateChanged = dateChanged ?: "",
        estimatedWeight = estimatedWeight ?: 0,
        extraartists = extraartists.toReleaseArtist(),
        formatQuantity = formatQuantity ?: -1,
        formats = releaseFormats,
        genres = genres ?: emptyList(),
        id = id ?: -1,
        identifiers = releaseIdentifiers,
        images = releaseImages,
        labels = labels.toReleaseSubinfo(),
        lowestPrice = lowestPrice ?: 0.0,
        masterId = masterId ?: 0,
        masterUrl = masterUrl ?: "",
        notes = notes ?: "",
        numForSale = numForSale ?: 0,
        released = released ?: "",
        releasedFormatted = releasedFormatted ?: "",
        resourceUrl = resourceUrl ?: "",
        series = series.toReleaseSubinfo(),
        status = status ?: "",
        styles = styles ?: emptyList(),
        thumb = thumb ?: "",
        title = title ?: "",
        tracklist = releaseTrackList,
        uri = uri ?: "",
        videos = releaseVideos,
        year = year ?: 0
    )

}

private fun List<ReleaseSubinfoDTO>?.toReleaseSubinfo(): List<ReleaseSubinfo> {
    return this?.map { dto ->
        ReleaseSubinfo(
            name = dto.name ?: "",
            catno = dto.catno ?: "",
            entityType = dto.entityType ?: "",
            entityTypeName = dto.entityTypeName ?: "",
            id = dto.id ?: -1,
            resourceUrl = dto.resourceUrl ?: ""
        )
    } ?: emptyList()
}

private fun List<ReleaseArtistDTO>?.toReleaseArtist(): List<ReleaseArtist> {
    return this?.map { dto ->
        ReleaseArtist(
            anv = dto.anv ?: "",
            id = dto.id ?: -1,
            join = dto.join ?: "",
            name = dto.name ?: "",
            resourceUrl = dto.resourceUrl ?: "",
            role = dto.role ?: "",
            tracks = dto.tracks ?: ""
        )
    } ?: emptyList()
}