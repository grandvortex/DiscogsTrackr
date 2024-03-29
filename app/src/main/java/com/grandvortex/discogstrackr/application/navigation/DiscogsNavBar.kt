package com.grandvortex.discogstrackr.application.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun DiscogsNavBar(currentRoute: String, navigateToScreen: (screen: TabScreen) -> Unit) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.primary) {
        bottomNavList.forEach { bottomNavItem ->
            NavigationBarItem(
                selected = currentRoute == bottomNavItem.route,
                onClick = { navigateToScreen(bottomNavItem) },
                icon = {
                    Icon(imageVector = bottomNavItem.icon, contentDescription = "")
                },
                label = { Text(text = stringResource(id = bottomNavItem.label)) }
            )
        }
    }
}
