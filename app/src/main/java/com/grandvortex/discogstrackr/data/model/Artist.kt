package com.grandvortex.discogstrackr.data.model

data class Artist(
    val aliases: List<Aliase>,
    val dataQuality: String,
    val id: Int,
    val images: List<Image>,
    val name: String,
    val namevariations: List<String>,
    val profile: String,
    val realname: String,
    val releasesUrl: String,
    val resourceUrl: String,
    val uri: String,
    val urls: List<String>
)

data class Aliase(
    val id: Int,
    val name: String,
    val resourceUrl: String
)

data class Image(
    val height: Int,
    val resourceUrl: String,
    val type: String,
    val uri: String,
    val uri150: String,
    val width: Int
)