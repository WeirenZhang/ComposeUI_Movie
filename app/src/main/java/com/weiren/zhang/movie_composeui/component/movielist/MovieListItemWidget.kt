package com.weiren.zhang.movie_composeui.component.movielist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.weiren.zhang.movie_composeui.R
import com.weiren.zhang.movie_composeui.model.movielist.MovieListModel

@Preview
@Composable
fun PreviewMovieListItemWidget() {

    Row(
        modifier = Modifier.padding(10.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.pm_fmen13186482_0001),
            contentDescription = stringResource(id = R.string.app_name),
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(100.dp, 142.dp)
        )
        Box(
            modifier = Modifier
                .size(142.dp)
                .weight(1f)
                .padding(start = 10.dp),
        ) {
            Box(modifier = Modifier.align(Alignment.TopStart)) {
                Column() {
                    Text(
                        text = "獅子王：木法沙",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Mufasa: The Lion King",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 5.dp),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2
                    )
                }
            }
            Box(
                modifier = Modifier.align(Alignment.BottomStart)
            ) {
                Text(
                    text = "12/19/2024",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
            }
        }
    }
}

@Composable
fun MovieListItemWidget(
    modifier: Modifier = Modifier,
    onClick: (model: MovieListModel) -> Unit,
    item: MovieListModel
) {

    Row(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                role = Role.Button,
                onClick = { onClick(item) },
            )
            .padding(10.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = item.thumb),
            contentDescription = null,
            modifier = Modifier.size(100.dp, 142.dp)
        )
        Box(
            modifier = Modifier
                .size(142.dp)
                .weight(1f)
                .padding(start = 10.dp),
        ) {
            Box(modifier = Modifier.align(Alignment.TopStart)) {
                Column() {
                    Text(
                        text = item.title,
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = item.en,
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 5.dp),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2
                    )
                }
            }
            Box(
                modifier = Modifier.align(Alignment.BottomStart)
            ) {
                Text(
                    text = item.release_movie_time,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
            }
        }
    }
}