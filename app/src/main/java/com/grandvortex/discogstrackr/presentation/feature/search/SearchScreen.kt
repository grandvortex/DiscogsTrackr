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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
    onClickItem: (Int) -> Unit,
    snackbarHostState: SnackbarHostState

) {
    val viewState by searchViewModel.viewStateFlow.collectAsStateWithLifecycle()
    val queryText = searchViewModel.queryText

    SearchScreen(
        modifier = modifier,
        viewState = viewState,
        queryText = queryText,
        onSearchActiveChanged = searchViewModel::onSearchActiveChanged,
        onSearchTriggered = searchViewModel::onSearchTriggered,
        onSearchQueryChanged = searchViewModel::onSearchQueryChanged,
        onClickItem = onClickItem,
        onClickRecentQueryItem = searchViewModel::onRecentSearchQueryClick,
        onClickDeleteRecentQueryItem = searchViewModel::onRecentSearchItemDeleted,
        onConsumedError = searchViewModel::onConsumedError,
        snackbarHostState = snackbarHostState
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewState: SearchViewState,
    queryText: String = "",
    onSearchActiveChanged: (Boolean) -> Unit,
    onSearchTriggered: () -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onClickItem: (Int) -> Unit,
    onClickRecentQueryItem: (String) -> Unit,
    onClickDeleteRecentQueryItem: (String) -> Unit,
    onConsumedError: () -> Unit,
    snackbarHostState: SnackbarHostState
) {
    val onSearchTriggeredFinal = {
        onSearchActiveChanged(false)
        onSearchTriggered()
    }

    if (viewState.error.isNotEmpty()) {
        LaunchedEffect(key1 = snackbarHostState) {
            snackbarHostState.showSnackbar(message = viewState.error)
            onConsumedError()
        }
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
        ) {
            RecentSearchContent(
                modifier = modifier,
                viewState = viewState,
                onClickRecentQueryItem = onClickRecentQueryItem,
                onClickDeleteItem = onClickDeleteRecentQueryItem
            )
        }
        SearchResultContent(
            viewState = viewState,
            onClickItem = onClickItem
        )
    }
}

@Composable
fun RecentSearchContent(
    modifier: Modifier = Modifier,
    viewState: SearchViewState,
    onClickRecentQueryItem: (String) -> Unit,
    onClickDeleteItem: (String) -> Unit
) {
    val recentSearchList = viewState.recentSearchData

    if (recentSearchList.isNullOrEmpty()) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = stringResource(id = R.string.no_recent_queries))
        }
    } else {
        LazyColumn(modifier = modifier.fillMaxSize(), contentPadding = PaddingValues(all = 4.dp)) {
            recentSearchList.forEach { recentQuery ->
                item {
                    RecentSearchQueryItem(
                        query = recentQuery.queryText,
                        onClickItem = { onClickRecentQueryItem(recentQuery.queryText) },
                        onClickItemDelete = { onClickDeleteItem(recentQuery.queryText) }
                    )
                }
            }
        }
    }
}

@Composable
fun SearchResultContent(
    modifier: Modifier = Modifier,
    viewState: SearchViewState,
    onClickItem: (Int) -> Unit
) {
    val list = viewState.searchResultData?.results
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
        LazyColumn(modifier = modifier.fillMaxSize(), contentPadding = PaddingValues(all = 4.dp)) {
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
            viewState = SearchViewState(),
            onSearchQueryChanged = {},
            onSearchTriggered = {},
            onSearchActiveChanged = {},
            onClickItem = {},
            onClickRecentQueryItem = {},
            onClickDeleteRecentQueryItem = {},
            onConsumedError = {},
            snackbarHostState = SnackbarHostState()
        )
    }
}
