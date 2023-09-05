package com.grandvortex.discogstrackr.data.mapper

import com.grandvortex.discogstrackr.domain.model.Community
import com.grandvortex.discogstrackr.domain.model.Format
import com.grandvortex.discogstrackr.domain.model.Pagination
import com.grandvortex.discogstrackr.domain.model.SearchResult
import com.grandvortex.discogstrackr.domain.model.SearchResults
import com.grandvortex.discogstrackr.domain.model.Urls
import com.grandvortex.discogstrackr.data.remote.dto.SearchResultsDTO
import com.grandvortex.discogstrackr.data.toResourceType

fun SearchResultsDTO.toSearchResults(): SearchResults {
    val remoteUrls = pagination?.urls
    val urls = Urls(
        last = remoteUrls?.last ?: "",
        next = remoteUrls?.next ?: "",
        prev = remoteUrls?.prev ?: "",
        first = remoteUrls?.prev ?: ""
    )

    val remotePagination = pagination
    val pagination = Pagination(
        items = remotePagination?.items ?: -1,
        page = remotePagination?.page ?: -1,
        pages = remotePagination?.pages ?: -1,
        perPage = remotePagination?.perPage ?: -1,
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
                name = remoteFormat.name ?: "",
                qty = remoteFormat.qty ?: "",
                text = remoteFormat.text ?: ""
            )
        } ?: emptyList()

        SearchResult(
            id = remoteResult.id ?: -1,
            barcode = remoteResult.barcode ?: emptyList(),
            catno = remoteResult.catno ?: "",
            community = community,
            country = remoteResult.country ?: "",
            coverImage = remoteResult.coverImage ?: "",
            format = remoteResult.format ?: emptyList(),
            formatQuantity = remoteResult.formatQuantity ?: -1,
            formats = formats,
            genre = remoteResult.genre ?: emptyList(),
            label = remoteResult.label ?: emptyList(),
            masterId = remoteResult.masterId ?: -1,
            masterUrl = remoteResult.masterUrl ?: "",
            resourceUrl = remoteResult.resourceUrl ?: "",
            style = remoteResult.style ?: emptyList(),
            thumb = remoteResult.thumb ?: "",
            title = remoteResult.title ?: "",
            type = toResourceType(remoteResult.type),
            uri = remoteResult.uri ?: "",
            year = remoteResult.year ?: ""
        )
    }

    return SearchResults(
        pagination = pagination,
        results = results
    )
}
