package com.grandvortex.discogstrackr.application.utils

import androidx.compose.ui.Modifier

fun Modifier.onCondition(
    condition: Boolean,
    onTrue: Modifier.() -> Modifier,
    onFalse: (Modifier.() -> Modifier)? = null
): Modifier {
    return if (condition) {
        then(onTrue(Modifier))
    } else if (onFalse != null) {
        then(onFalse(Modifier))
    } else {
        this
    }
}