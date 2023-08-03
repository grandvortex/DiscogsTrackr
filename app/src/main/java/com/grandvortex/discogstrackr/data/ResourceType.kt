package com.grandvortex.discogstrackr.data

enum class ResourceType(val type: String) {
    MASTER("master"), RELEASE("release"), LABEL("label"), ARTIST("artist"), UNKNOWN("unknown")
}

fun toResourceType(type: String?): ResourceType {
    return when (type) {
        ResourceType.ARTIST.type -> ResourceType.ARTIST
        ResourceType.LABEL.type -> ResourceType.LABEL
        ResourceType.MASTER.type -> ResourceType.MASTER
        ResourceType.RELEASE.type -> ResourceType.RELEASE
        else -> ResourceType.UNKNOWN
    }
}
