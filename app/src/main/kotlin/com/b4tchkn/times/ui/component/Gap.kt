package com.b4tchkn.times.ui.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

/*
Reference
@see https://github.com/wasabeef/compose-gap
*/
@Composable
fun RowScope.Gap(width: Dp, modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.width(width))
}

@Composable
fun ColumnScope.Gap(height: Dp, modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.height(height))
}
