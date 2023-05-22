package com.grandvortex.discogstrackr.presentation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun DiscogsNavBar(currentRoute: String, navActions: NavigationActions) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.primary) {
        bottomNavList.forEach { bottomNavItem ->
            NavigationBarItem(
                selected = currentRoute == bottomNavItem.route,
                onClick = { navActions.getNavAction(bottomNavItem) },
                icon = {
                    Icon(imageVector = bottomNavItem.icon, contentDescription = "")
                },
                label = { Text(text = stringResource(id = bottomNavItem.label)) }
            )
        }
    }
}
