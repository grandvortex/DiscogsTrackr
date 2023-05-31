package com.grandvortex.discogstrackr

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.grandvortex.discogstrackr.feature.search.SEARCH_ROUTE
import com.grandvortex.discogstrackr.navigation.DiscogsNavBar
import com.grandvortex.discogstrackr.navigation.DiscogsNavHost
import com.grandvortex.discogstrackr.navigation.Screen
import com.grandvortex.discogstrackr.navigation.navigateToScreen
import com.grandvortex.discogstrackr.theme.DiscogsTrackrTheme
import com.grandvortex.discogstrackr.utils.DevicePreviews

@Composable
fun DiscogsTrackrApp() {
    DiscogsTrackrTheme {
        val navController = rememberNavController()

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route ?: SEARCH_ROUTE

        val snackbarHostState = remember { SnackbarHostState() }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            topBar = {},
            bottomBar = {
                DiscogsNavBar(
                    currentRoute
                ) { screen: Screen -> navigateToScreen(navController, screen) }
            }
        ) { paddingValues ->
            DiscogsNavHost(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                navController = navController
            )
        }
    }
}

@DevicePreviews
@Composable
fun DiscogsTrackrAppPreview() {
    DiscogsTrackrApp()
}
