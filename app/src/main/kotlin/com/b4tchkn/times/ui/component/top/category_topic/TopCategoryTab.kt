package com.b4tchkn.times.ui.component.top.category_topic

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.b4tchkn.times.model.GoogleNewsServiceTopicTypeModel
import com.b4tchkn.times.ui.component.AppDivider
import com.b4tchkn.times.ui.theme.AppColor

@Composable
fun TopCategoryTopicTab(
    selectedTabIndex: Int,
    onTabClicked: (index: Int) -> Unit,
) {
    val context = LocalContext.current
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = AppColor.Transparent,
        edgePadding = 16.dp,
        divider = { AppDivider(startIndent = 16.dp, endIndent = 16.dp) }
    ) {
        repeat(GoogleNewsServiceTopicTypeModel.values().size) {
            Tab(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp)),
                selected = it == selectedTabIndex,
                onClick = {
                    onTabClicked(it)
                },
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = GoogleNewsServiceTopicTypeModel.label(
                        type = GoogleNewsServiceTopicTypeModel.values()[it],
                        context = context,
                    ),
                    fontSize = 16.sp,
                )
            }
        }
    }
}
