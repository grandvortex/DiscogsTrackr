package com.grandvortex.discogstrackr.data

import com.grandvortex.discogstrackr.data.model.Community
import com.grandvortex.discogstrackr.data.model.Format
import com.grandvortex.discogstrackr.data.model.Pagination
import com.grandvortex.discogstrackr.data.model.SearchResult
import com.grandvortex.discogstrackr.data.model.SearchResults
import com.grandvortex.discogstrackr.data.model.Urls
import com.grandvortex.discogstrackr.data.remote.model.SearchResultsRemote

fun SearchResultsRemote.toSearchResults(): SearchResults {
    val remoteUrls = pagination.urls
    val urls = Urls(
        last = remoteUrls?.last ?: "",
        next = remoteUrls?.next ?: ""
    )

    val remotePagination = pagination
    val pagination = Pagination(
        items = remotePagination.items,
        page = remotePagination.page,
        pages = remotePagination.pages,
        perPage = remotePagination.perPage,
        urls = urls
    )

    val results: List<SearchResult> = results.map { remoteResult ->
        val remoteCommunity = remoteResult.community
        val community = Community(
            have = remoteCommunity?.have ?: 0,
            want = remoteCommunity?.want ?: 0
        )

        val formats: List<Format> = remoteResult.formats?.map { remoteFormat ->
            Format(
                descriptions = remoteFormat.descriptions ?: emptyList(),
                name = remoteFormat.name,
                qty = remoteFormat.qty,
                text = remoteFormat.text
            )
        } ?: emptyList()

        SearchResult(
            id = remoteResult.id,
            barcode = remoteResult.barcode ?: emptyList(),
            catno = remoteResult.catno,
            community = community,
            country = remoteResult.country,
            coverImage = remoteResult.coverImage,
            format = remoteResult.format ?: emptyList(),
            formatQuantity = remoteResult.formatQuantity,
            formats = formats,
            genre = remoteResult.genre ?: emptyList(),
            label = remoteResult.label ?: emptyList(),
            masterId = remoteResult.masterId,
            masterUrl = remoteResult.masterUrl,
            resourceUrl = remoteResult.resourceUrl,
            style = remoteResult.style ?: emptyList(),
            thumb = remoteResult.thumb,
            title = remoteResult.title,
            type = remoteResult.type,
            uri = remoteResult.uri,
            year = remoteResult.year
        )
    }

    return SearchResults(
        pagination = pagination,
        results = results
    )
}
