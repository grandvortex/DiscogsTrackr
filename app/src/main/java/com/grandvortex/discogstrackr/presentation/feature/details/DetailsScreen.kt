package com.grandvortex.discogstrackr.presentation.feature.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DetailsRoute(id: Int) {
    DetailsScreen(id)
}

@Composable
fun DetailsScreen(id: Int) {
    Text(text = id.toString())
}
