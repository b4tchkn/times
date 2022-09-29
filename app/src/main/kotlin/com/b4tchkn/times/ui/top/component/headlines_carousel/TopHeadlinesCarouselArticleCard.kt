package com.b4tchkn.times.ui.top.component.headlines_carousel

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.b4tchkn.times.model.NewsArticleModel
import com.b4tchkn.times.ui.component.Gap

@Composable
fun TopHeadlinesCarouselArticleCard(
    modifier: Modifier = Modifier,
    article: NewsArticleModel,
    onCardClicked: () -> Unit,
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .width(240.dp)
                .clip(RoundedCornerShape(16.dp))
                .clickable {
                    onCardClicked()
                }
                .padding(bottom = 16.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(16.dp)),
                model = article.urlToImage,
                contentDescription = article.title,
                contentScale = ContentScale.Crop,
            )
            article.title?.let {
                Gap(height = 8.dp)
                Text(
                    text = it,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
