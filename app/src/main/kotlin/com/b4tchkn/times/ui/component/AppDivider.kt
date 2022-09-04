package com.b4tchkn.times.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AppDivider(
    startIndent: Dp = 0.dp,
    endIndent: Dp = 0.dp,
    thickness: Dp = 1.dp,
) {
    Box(
        modifier = Modifier.padding(
            start = startIndent,
            end = endIndent,
        )
    ) {
        Divider(
            thickness = thickness,
        )
    }
}
