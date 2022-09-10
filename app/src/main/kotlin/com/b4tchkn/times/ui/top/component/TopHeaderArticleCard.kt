package com.b4tchkn.times.ui.top.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.b4tchkn.times.R
import com.b4tchkn.times.model.NewsArticleModel
import com.b4tchkn.times.ui.component.Gap
import com.b4tchkn.times.ui.theme.AppColor
import com.b4tchkn.times.util.formatBeforeDateTimeFromNowString

@Composable
fun TopHeader(
    article: NewsArticleModel,
    onClicked: () -> Unit
) {
    val context = LocalContext.current
    val gradientBlackWhite = Brush.verticalGradient(
        0f to AppColor.White.copy(alpha = 0.0f),
        1000f to AppColor.Black.copy(alpha = 0.8f)
    )
    Box(
        modifier = Modifier
            .height(320.dp)
            .clip(RoundedCornerShape(bottomEnd = 24.dp, bottomStart = 24.dp))
            .clickable { onClicked() }
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize(),
            model = article.urlToImage,
            contentDescription = article.title,
            contentScale = ContentScale.FillHeight,
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(gradientBlackWhite)
                .padding(start = 16.dp, end = 16.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.Bottom,
        ) {
            Text(
                text = article.title ?: "",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
            Gap(height = 16.dp)
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = formatBeforeDateTimeFromNowString(context, article.publishedAt),
                    fontSize = 14.sp,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = stringResource(id = R.string.top_header_article_card_read_article),
                    fontSize = 14.sp,
                )
                Gap(width = 4.dp)
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = rememberVectorPainter(image = Icons.Default.ArrowForward),
                    contentDescription = null,
                )
            }
        }
    }
}
