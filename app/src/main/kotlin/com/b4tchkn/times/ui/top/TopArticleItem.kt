package com.b4tchkn.times.ui.top

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.b4tchkn.times.ui.component.Gap

@Composable
fun TopArticleItem(
    modifier: Modifier,
    title: String,
    source: String,
    publishDate: String,
) {
    Column(
        modifier = modifier.padding(
            vertical = 8.dp,
            horizontal = 16.dp,
        )
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        Gap(4.dp)
        Row {
            Text(
                text = source,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = publishDate,
                fontSize = 14.sp
            )
        }
    }
}
