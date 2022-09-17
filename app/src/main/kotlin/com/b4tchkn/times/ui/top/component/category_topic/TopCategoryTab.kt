package com.b4tchkn.times.ui.top.component.category_topic

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.b4tchkn.times.data.GoogleNewsServiceTopicType
import com.b4tchkn.times.ui.theme.AppColor

@Composable
fun TopCategoryTopicTab(
    selectedTabIndex: Int,
    onTabClicked: (index: Int) -> Unit,
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = AppColor.Transparent,
        edgePadding = 16.dp,
    ) {
        repeat(GoogleNewsServiceTopicType.values().size) {
            Tab(
                selected = it == selectedTabIndex,
                onClick = {
                    onTabClicked(it)
                },
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = GoogleNewsServiceTopicType.values()[it].name,
                )
            }
        }
    }
}