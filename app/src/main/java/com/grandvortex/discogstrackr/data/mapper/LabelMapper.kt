package com.grandvortex.discogstrackr.data.mapper

import com.grandvortex.discogstrackr.data.model.Label
import com.grandvortex.discogstrackr.data.model.LabelImage
import com.grandvortex.discogstrackr.data.model.ParentLabel
import com.grandvortex.discogstrackr.data.model.Sublabel
import com.grandvortex.discogstrackr.data.remote.dto.LabelDTO

fun LabelDTO.toLabel(): Label {
    val images = images?.map { dto ->
        LabelImage(
            height = dto.height ?: 0,
            resourceUrl = dto.resourceUrl ?: "",
            type = dto.type ?: "",
            uri = dto.uri ?: "",
            uri150 = dto.uri150 ?: "",
            width = dto.width ?: 0
        )
    } ?: emptyList()

    val sublabels = sublabels?.map { dto ->
        Sublabel(
            id = dto.id ?: -1,
            name = dto.name ?: "",
            resourceUrl = dto.resourceUrl ?: ""
        )
    } ?: emptyList()

    val parentLabel = ParentLabel(
        id = id ?: -1,
        name = name ?: "",
        resourceUrl = resourceUrl ?: ""
    )

    return Label(
        contactInfo = contactInfo ?: "",
        dataQuality = dataQuality ?: "",
        id = id ?: -1,
        images = images,
        name = name ?: "",
        parentLabel = parentLabel,
        profile = profile ?: "",
        releasesUrl = releasesUrl ?: "",
        resourceUrl = resourceUrl ?: "",
        sublabels = sublabels,
        uri = uri ?: "",
        urls = urls ?: emptyList()
    )
}
