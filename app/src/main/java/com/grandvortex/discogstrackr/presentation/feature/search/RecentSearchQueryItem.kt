package com.grandvortex.discogstrackr.presentation.feature.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RecentSearchQueryItem(
    modifier: Modifier = Modifier,
    query: String,
    onClickItem: () -> Unit,
    onClickItemDelete: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(start = 4.dp, end = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = modifier.fillMaxWidth().clickable(onClick = onClickItem).weight(1f),
            text = query,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 24.sp
        )

        Icon(
            modifier = modifier.size(24.dp).clickable {
                onClickItemDelete()
            },
            imageVector = Icons.Outlined.Cancel,
            contentDescription = null
        )
    }
}

@Composable
@Preview(
    showSystemUi = false,
    showBackground = false
)
fun PreviewRecentSearchQueryItem() {
    RecentSearchQueryItem(query = "LTJ Bukem", onClickItem = {}, onClickItemDelete = {})
}
