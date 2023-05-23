package com.grandvortex.discogstrackr.presentation

import androidx.compose.foundation.layout.Row
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
import com.grandvortex.discogstrackr.theme.DiscogsTrackrTheme

@Composable
fun DiscogsTrackrApp() {
    DiscogsTrackrTheme {
        val navController = rememberNavController()

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route ?: NavDestinations.SEARCH_ROUTE

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
            Row(
                modifier = Modifier.fillMaxSize().padding(paddingValues)
            ) { DiscogsNavHost(navController = navController) }
        }
    }
}
