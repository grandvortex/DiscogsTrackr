package com.grandvortex.discogstrackr.presentation.feature.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun RecentSearchQueryItem(modifier: Modifier = Modifier, query: String, onClickItem: () -> Unit) {
    Text(
        modifier = modifier.fillMaxWidth().clickable(onClick = onClickItem),
        text = query,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontSize = 24.sp
    )
}
