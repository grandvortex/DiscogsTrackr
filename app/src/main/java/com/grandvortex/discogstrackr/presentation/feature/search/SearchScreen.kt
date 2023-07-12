package com.grandvortex.discogstrackr.presentation.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.grandvortex.discogstrackr.R
import com.grandvortex.discogstrackr.theme.DiscogsTrackrTheme
import com.grandvortex.discogstrackr.utils.DevicePreviews

@Composable
fun SearchRoute(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel = hiltViewModel(),
    onClickItem: (Int) -> Unit
) {
    val viewState by searchViewModel.viewState.collectAsStateWithLifecycle()
    val queryText by searchViewModel.queryText

    SearchScreen(
        modifier = modifier,
        viewState = viewState,
        queryText = queryText,
        onSearchActiveChanged = searchViewModel::onSearchActiveChanged,
        onSearchTriggered = searchViewModel::onSearchTriggered,
        onSearchQueryChanged = searchViewModel::onSearchQueryChanged,
        onClickItem = onClickItem
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewState: SearchState,
    queryText: String = "",
    onSearchActiveChanged: (Boolean) -> Unit,
    onSearchTriggered: () -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onClickItem: (Int) -> Unit
) {
    val onSearchTriggeredFinal = {
        onSearchActiveChanged(false)
        onSearchTriggered()
    }

    Column(
        modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.secondary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(
            modifier = modifier.fillMaxWidth().onKeyEvent {
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
        SearchResultContent(viewState = viewState, onClickItem = onClickItem)
    }
}

@Composable
fun SearchResultContent(
    modifier: Modifier = Modifier,
    viewState: SearchState,
    onClickItem: (Int) -> Unit
) {
    val list = viewState.data?.results
    val listEmpty = list?.isEmpty() ?: false

    if (viewState.isLoading) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = stringResource(id = R.string.loading))
        }
    } else if (listEmpty) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = stringResource(id = R.string.search_result_empty))
        }
    } else if (list == null) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = stringResource(id = R.string.search_hint))
        }
    } else {
        LazyColumn(modifier = modifier, contentPadding = PaddingValues(all = 4.dp)) {
            list.forEach { result ->
                val id = result.id
                item(key = id) {
                    SearchResultItem(
                        type = result.type,
                        info = result.title,
                        imageUrl = result.coverImage,
                        onClick = { onClickItem(id) }
                    )
                }
            }
        }
    }
}

@DevicePreviews
@Composable
fun SearchScreenPreview() {
    DiscogsTrackrTheme {
        SearchScreen(
            viewState = SearchState(),
            onSearchQueryChanged = {},
            onSearchTriggered = {},
            onSearchActiveChanged = {},
            onClickItem = {}
        )
    }
}