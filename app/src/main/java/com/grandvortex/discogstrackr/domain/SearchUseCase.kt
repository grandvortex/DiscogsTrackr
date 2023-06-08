package com.grandvortex.discogstrackr.domain

import com.grandvortex.discogstrackr.data.remote.repository.SearchRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    operator fun invoke(query: String) {
    }
}
