package com.weiren.zhang.movie_composeui.component.movieinfomain

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.weiren.zhang.movie_composeui.R
import com.weiren.zhang.movie_composeui.model.movieinfomain.VideoModel

@Preview
@Composable
fun PreviewVideoItemWidget() {
    Row(
        modifier = Modifier.padding(10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.sddefault),
            contentDescription = stringResource(id = R.string.app_name),
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(100.dp, 78.dp)
        )
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = "動作喜劇鉅獻【第一聖拳】Holy Punch 電影預告 黑道牧師X流氓僧人X刑警巫覡 史上最強復仇者聯盟！1/10(五)各顯神通 (1:30)",
            color = Color.Gray,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 3
        )
    }
}

@Composable
fun VideoItemWidget(
    modifier: Modifier = Modifier,
    onClick: (model: VideoModel) -> Unit,
    item: VideoModel,
) {
    Row(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                role = Role.Button,
                onClick = { onClick(item) },
            )
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = item.cover),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(100.dp, 78.dp)
        )
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = item.title,
            color = Color.Gray,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 3
        )
    }
}