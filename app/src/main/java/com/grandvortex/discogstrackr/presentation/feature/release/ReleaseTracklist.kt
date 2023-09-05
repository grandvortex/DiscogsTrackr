package com.grandvortex.discogstrackr.presentation.feature.release

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.grandvortex.discogstrackr.domain.model.Release

@Composable
fun ReleaseTracklist(modifier: Modifier, release: Release) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) { Text(text = "ReleaseTracklist") }
}