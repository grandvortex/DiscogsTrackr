package com.grandvortex.discogstrackr.presentation.utils

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

/**
 * Add this multipreview annotation to a composable to render the composable in extra small and
 * extra large font size.
 */
@Preview(
    name = "small font",
    group = "font scales",
    fontScale = 0.5f
)
@Preview(
    name = "large font",
    group = "font scales",
    fontScale = 1.5f
)
annotation class FontScalePreviews

/**
 * Add this multipreview annotation to a composable to render the composable on various device
 * sizes: phone, foldable, and tablet.
 */
@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "phone",
    group = "devices",
    device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480"
)
@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "landscape",
    group = "devices",
    device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480"
)
@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "foldable",
    group = "devices",
    device = "spec:shape=Normal,width=673,height=841,unit=dp,dpi=480"
)
@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "tablet",
    group = "devices",
    device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480"
)
annotation class DevicePreviews

/**
 * Add this multipreview annotation to a composable to render the composable in various common
 * configurations:
 * - Dark theme
 * - Small and large font size
 * - various device sizes
 *
 * _Note: Combining multipreview annotations doesn't mean all the different combinations are shown.
 * Instead, each multipreview annotation acts by its own and renders only its own variants.
 */
@Preview(
    name = "dark theme",
    group = "themes",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@FontScalePreviews
@DevicePreviews
annotation class CompletePreviews
