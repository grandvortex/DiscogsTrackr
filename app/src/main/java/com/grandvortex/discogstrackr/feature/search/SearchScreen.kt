package com.grandvortex.discogstrackr.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.grandvortex.discogstrackr.R
import com.grandvortex.discogstrackr.theme.DiscogsTrackrTheme
import com.grandvortex.discogstrackr.utils.DevicePreviews

@Composable
fun SearchRoute(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val viewState by searchViewModel.viewState.collectAsStateWithLifecycle()
    val queryText by searchViewModel.queryText

    SearchScreen(
        modifier = modifier,
        viewState = viewState,
        queryText = queryText,
        onSearchActiveChanged = searchViewModel::onSearchActiveChanged,
        onSearchTriggered = searchViewModel::onSearchTriggered,
        onSearchQueryChanged = searchViewModel::onSearchQueryChanged
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewState: SearchState = SearchState(),
    queryText: String = "",
    onSearchActiveChanged: (Boolean) -> Unit = {},
    onSearchTriggered: () -> Unit = {},
    onSearchQueryChanged: (String) -> Unit = {}
) {
    val onSearchTriggeredFinal = {
        onSearchActiveChanged(false)
        onSearchTriggered()
    }

    Box(
        modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.secondary)
    ) {
        SearchBar(
            modifier = modifier.fillMaxWidth().align(Alignment.TopCenter).onKeyEvent {
                if (it.key == Key.Enter) {
                    onSearchTriggeredFinal()
                    true
                } else {
                    false
                }
            },
            query = queryText,
            onQueryChange = { onSearchQueryChanged(it) },
            onSearch = {
                onSearchTriggeredFinal()
            },
            active = viewState.isSearchActive,
            onActiveChange = { onSearchActiveChanged(it) },
            placeholder = { Text(stringResource(id = R.string.search_hint)) },
            leadingIcon = {
                Icon(
                    modifier = modifier.clickable {
                        onSearchTriggeredFinal()
                    },
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            trailingIcon = {
                if (viewState.isSearchActive) {
                    Icon(
                        modifier = modifier.clickable {
                            if (queryText.isEmpty()) {
                                onSearchActiveChanged(false)
                            } else {
                                onSearchQueryChanged("")
                            }
                        },
                        imageVector = Icons.Default.Close,
                        contentDescription = null
                    )
                }
            }
        ) {}
    }
}

@DevicePreviews
@Composable
fun SearchScreenPreview() {
    DiscogsTrackrTheme {
        SearchScreen()
    }
}
