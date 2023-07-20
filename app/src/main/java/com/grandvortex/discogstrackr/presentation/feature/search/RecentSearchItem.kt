package com.grandvortex.discogstrackr.presentation.feature.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RecentSearchItem(modifier: Modifier = Modifier, query: String) {
    Text(
        modifier = modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp),
        text = query,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontSize = 24.sp
    )
}
