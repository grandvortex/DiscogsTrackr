package com.grandvortex.discogstrackr.presentation.feature.release

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.grandvortex.discogstrackr.R
import com.grandvortex.discogstrackr.domain.model.Release
import kotlinx.coroutines.launch

typealias ComposableFun = @Composable (modifier: Modifier, release: Release) -> Unit

sealed class ReleaseTabItem(@StringRes val title: Int, val composable: ComposableFun) {
    data object InfoTabItem :
        ReleaseTabItem(title = R.string.release_tabitem_info, composable = { modifier, release ->
            ReleaseInfo(modifier = modifier, release = release)
        })

    data object TracklistTabItem : ReleaseTabItem(
        title = R.string.release_tabitem_tracklist,
        composable = { modifier, release ->
            ReleaseTracklist(modifier = modifier, release = release)
        })
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReleaseRoute(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    viewModel: ReleaseViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewStateFlow.collectAsStateWithLifecycle(ReleaseViewState())
    val listOfTabs = listOf(ReleaseTabItem.InfoTabItem, ReleaseTabItem.TracklistTabItem)
    val pagerState = rememberPagerState { listOfTabs.size }

    ReleaseScreen(
        modifier = modifier,
        snackbarHostState = snackbarHostState,
        pagerState = pagerState,
        tabs = listOfTabs,
        viewState = viewState,
        onConsumeError = viewModel::onConsumeError
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReleaseScreen(
    modifier: Modifier,
    snackbarHostState: SnackbarHostState,
    pagerState: PagerState,
    tabs: List<ReleaseTabItem>,
    viewState: ReleaseViewState,
    onConsumeError: () -> Unit
) {
    if (viewState.error.isNotEmpty()) {
        LaunchedEffect(key1 = snackbarHostState) {
            snackbarHostState.showSnackbar(viewState.error)
            onConsumeError()
        }
    }

    if (viewState.isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (viewState.releaseData == null) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
            Text(text = stringResource(id = R.string.unknown_release))
        }
    } else {
        ReleaseContent(
            modifier = modifier,
            tabs = tabs,
            pagerState = pagerState,
            release = viewState.releaseData
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReleaseContent(
    modifier: Modifier,
    tabs: List<ReleaseTabItem>,
    pagerState: PagerState,
    release: Release
) {
    Column(modifier = modifier.fillMaxSize()) {
        // Release image
        val image = if (release.images.isNotEmpty()) release.images.first().resourceUrl else ""
        AsyncImage(
            modifier = modifier
                .fillMaxWidth()
                .height(300.dp),
            model = ImageRequest.Builder(context = LocalContext.current).data(image).crossfade(true)
                .size(Size.ORIGINAL).build(),
            error = painterResource(R.drawable.broken_image),
            placeholder = painterResource(id = R.drawable.loading_image),
            contentDescription = release.title,
            contentScale = ContentScale.Crop
        )

        // Release info and tracklist
        Tabs(tabs = tabs, pagerState = pagerState)
        HorizontalPager(modifier = modifier.weight(1f), state = pagerState) { page ->
            tabs[page].composable(modifier, release)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tabs(tabs: List<ReleaseTabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()

    TabRow(
        modifier = Modifier.fillMaxWidth(),
        selectedTabIndex = pagerState.currentPage,
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                text = { Text(text = stringResource(id = tab.title)) },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}