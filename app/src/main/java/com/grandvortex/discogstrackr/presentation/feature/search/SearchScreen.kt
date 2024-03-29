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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.grandvortex.discogstrackr.R
import com.grandvortex.discogstrackr.application.theme.DiscogsTrackrTheme
import com.grandvortex.discogstrackr.data.ResourceType
import com.grandvortex.discogstrackr.presentation.feature.artist.navigateToArtistScreen
import com.grandvortex.discogstrackr.presentation.feature.label.navigateToLabelScreen
import com.grandvortex.discogstrackr.presentation.feature.release.navigateToReleaseScreen
import com.grandvortex.discogstrackr.presentation.utils.DevicePreviews

@Composable
fun SearchRoute(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState,
    navController: NavController
) {
    val viewState by searchViewModel.viewStateFlow.collectAsStateWithLifecycle(SearchViewState())
    val queryText = searchViewModel.queryText

    val onClickItem = { type: ResourceType, id: Int ->
        when (type) {
            ResourceType.ARTIST -> {
                navController.navigateToArtistScreen(id)
            }

            ResourceType.LABEL -> {
                navController.navigateToLabelScreen(id)
            }

            ResourceType.RELEASE -> {
                navController.navigateToReleaseScreen(id)
            }

            ResourceType.MASTER -> {}
            ResourceType.UNKNOWN -> {}
        }
    }

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
        onConsumeError = searchViewModel::onConsumeError,
        snackbarHostState = snackbarHostState
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    modifier: Modifier,
    viewState: SearchViewState,
    queryText: String = "",
    onSearchActiveChanged: (Boolean) -> Unit,
    onSearchTriggered: () -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onClickItem: (ResourceType, Int) -> Unit,
    onClickRecentQueryItem: (String) -> Unit,
    onClickDeleteRecentQueryItem: (String) -> Unit,
    onConsumeError: () -> Unit,
    snackbarHostState: SnackbarHostState
) {
    // Triggers search and updates searchbars' active state
    val onSearchTriggeredFinal = {
        onSearchActiveChanged(false)
        onSearchTriggered()
    }

    // Display errors
    if (viewState.error.isNotEmpty()) {
        LaunchedEffect(key1 = snackbarHostState) {
            snackbarHostState.showSnackbar(message = viewState.error)
            onConsumeError()
        }
    }

    // Display main content
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(modifier = modifier
            .fillMaxWidth()
            .onKeyEvent {
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
                    }, imageVector = Icons.Default.Search, contentDescription = null
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
                        }, imageVector = Icons.Default.Close, contentDescription = null
                    )
                }
            }) {
            // Display recently searched queries
            RecentSearchContent(
                modifier = modifier,
                viewState = viewState,
                onClickRecentQueryItem = onClickRecentQueryItem,
                onClickDeleteItem = onClickDeleteRecentQueryItem
            )
        }
        // Display searched content
        SearchResultContent(
            viewState = viewState, onClickItem = onClickItem
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
                    RecentSearchQueryItem(query = recentQuery.queryText,
                        onClickItem = { onClickRecentQueryItem(recentQuery.queryText) },
                        onClickItemDelete = { onClickDeleteItem(recentQuery.queryText) })
                }
            }
        }
    }
}

@Composable
fun SearchResultContent(
    modifier: Modifier = Modifier,
    viewState: SearchViewState,
    onClickItem: (ResourceType, Int) -> Unit
) {
    val list = viewState.searchResultData?.results
    val listEmpty = list?.isEmpty() ?: false

    if (viewState.isLoading) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color.White)
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
                item(key = result.id) {
                    SearchResultItem(type = result.type,
                        info = result.title,
                        imageUrl = result.coverImage,
                        onClick = { onClickItem(result.type, result.id) })
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
            onClickItem = { _, _ -> },
            onClickRecentQueryItem = {},
            onClickDeleteRecentQueryItem = {},
            onConsumeError = {},
            snackbarHostState = SnackbarHostState(),
            modifier = Modifier
        )
    }
}
