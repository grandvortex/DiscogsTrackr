package com.grandvortex.discogstrackr.data.mapper

import com.grandvortex.discogstrackr.domain.model.Aliase
import com.grandvortex.discogstrackr.domain.model.Artist
import com.grandvortex.discogstrackr.domain.model.Image
import com.grandvortex.discogstrackr.domain.model.Member
import com.grandvortex.discogstrackr.data.remote.dto.ArtistDTO

fun ArtistDTO.toArtist(): Artist {
    val aliaseList = aliases?.map { dto ->
        Aliase(
            id = dto.id ?: -1, name = dto.name ?: "", resourceUrl = dto.resourceUrl ?: ""
        )
    } ?: emptyList()

    val imageList = images?.map { dto ->
        Image(
            height = dto.height ?: 0,
            resourceUrl = dto.resourceUrl ?: "",
            type = dto.type ?: "",
            uri = dto.uri ?: "",
            uri150 = dto.uri150 ?: "",
            width = dto.width ?: 0
        )
    } ?: emptyList()

    val membersList = members?.map { dto ->
        Member(
            active = dto.active ?: false,
            id = dto.id ?: -1,
            name = dto.name ?: "",
            resourceUrl = dto.resourceUrl ?: ""
        )
    } ?: emptyList()

    return Artist(
        aliases = aliaseList,
        dataQuality = dataQuality ?: "",
        id = id ?: 0,
        images = imageList,
        name = name ?: "",
        namevariations = namevariations ?: emptyList(),
        profile = profile ?: "",
        realname = realname ?: "",
        releasesUrl = releasesUrl ?: "",
        resourceUrl = resourceUrl ?: "",
        uri = uri ?: "",
        urls = urls ?: emptyList(),
        members = membersList
    )
}
