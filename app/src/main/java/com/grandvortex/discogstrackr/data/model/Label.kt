package com.grandvortex.discogstrackr.data.model

data class Label(
    val contactInfo: String,
    val dataQuality: String,
    val id: Int,
    val images: List<LabelImage>,
    val name: String,
    val parentLabel: ParentLabel,
    val profile: String,
    val releasesUrl: String,
    val resourceUrl: String,
    val sublabels: List<Sublabel>,
    val uri: String,
    val urls: List<String>
)

data class LabelImage(
    val height: Int,
    val resourceUrl: String,
    val type: String,
    val uri: String,
    val uri150: String,
    val width: Int
)

data class ParentLabel(
    val id: Int,
    val name: String,
    val resourceUrl: String
)

data class Sublabel(
    val id: Int,
    val name: String,
    val resourceUrl: String
)
