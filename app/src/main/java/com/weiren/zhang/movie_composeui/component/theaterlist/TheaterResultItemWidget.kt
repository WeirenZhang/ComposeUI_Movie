package com.weiren.zhang.movie_composeui.component.theaterlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.weiren.zhang.movie_composeui.R
import com.weiren.zhang.movie_composeui.component.VerticalGrid
import com.weiren.zhang.movie_composeui.model.movielist.MovieListModel
import com.weiren.zhang.movie_composeui.model.theaterlist.TheaterResultModel

@Preview
@Composable
fun PreviewTheaterResultItemWidget() {

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
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
                    .weight(1f)
                    .padding(start = 10.dp),
            ) {
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
                    Image(
                        painter = painterResource(id = R.drawable.ad_1),
                        contentDescription = stringResource(id = R.string.app_name),
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .padding(top = 10.dp, bottom = 10.dp)
                            .size(50.dp, 50.dp)

                    )
                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxWidth(), columns = GridCells.Fixed(3)
                    ) {

                        items(2) { item ->

                            ElevatedCard(
                                colors = CardDefaults.cardColors(
                                    containerColor = colorResource(R.color.c840aa),
                                ),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 6.dp
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp)
                            ) {
                                Text(
                                    text = "英文版",
                                    color = Color.White,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(5.dp),
                                    textAlign = TextAlign.Center,
                                    fontSize = 14.sp,
                                )
                            }
                        }
                    }
                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxWidth(), columns = GridCells.Fixed(3)
                    ) {

                        items(2) { item ->

                            ElevatedCard(
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White,
                                ),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 6.dp
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp)
                            ) {
                                Text(
                                    text = "16:10",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(5.dp),
                                    textAlign = TextAlign.Center,
                                    fontSize = 14.sp,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TheaterResultItemWidget(
    modifier: Modifier = Modifier,
    onClick: (model: MovieListModel) -> Unit,
    item: TheaterResultModel,
) {

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                role = Role.Button,
                onClick = {
                    onClick(
                        MovieListModel(
                            item.theaterlist_name,
                            item.length,
                            "",
                            item.release_foto,
                            item.id
                        )
                    )
                },
            )
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            modifier = modifier.padding(10.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = item.release_foto),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = modifier.size(100.dp, 142.dp)
            )
            Box(
                modifier = modifier
                    .weight(1f)
                    .padding(start = 10.dp),
            ) {
                Column() {
                    Text(
                        text = item.theaterlist_name,
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = item.length,
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = modifier.padding(top = 5.dp),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2
                    )
                    Image(
                        painter = rememberAsyncImagePainter(model = item.icon),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = modifier
                            .padding(top = 10.dp, bottom = 10.dp)
                            .size(50.dp, 50.dp)

                    )
                    repeat(item.types.size) { index ->

                        VerticalGrid(columnCount = 2) {
                            item.types[index].types.map {
                                {
                                    ElevatedCard(
                                        colors = CardDefaults.cardColors(
                                            containerColor = colorResource(R.color.c840aa),
                                        ),
                                        elevation = CardDefaults.cardElevation(
                                            defaultElevation = 6.dp
                                        ),
                                        modifier = modifier
                                            .fillMaxWidth()
                                            .padding(5.dp)
                                    ) {
                                        Text(
                                            text = it.type,
                                            color = Color.White,
                                            modifier = modifier
                                                .fillMaxWidth()
                                                .padding(5.dp),
                                            textAlign = TextAlign.Center,
                                            fontSize = 14.sp,
                                        )
                                    }
                                }
                            }
                        }

                        VerticalGrid(columnCount = 2) {
                            item.types[index].times.map {
                                {
                                    ElevatedCard(
                                        colors = CardDefaults.cardColors(
                                            containerColor = Color.White,
                                        ),
                                        elevation = CardDefaults.cardElevation(
                                            defaultElevation = 6.dp
                                        ),
                                        modifier = modifier
                                            .fillMaxWidth()
                                            .padding(5.dp)
                                    ) {
                                        Text(
                                            text = it.time,
                                            color = Color.Gray,
                                            modifier = modifier
                                                .fillMaxWidth()
                                                .padding(5.dp),
                                            textAlign = TextAlign.Center,
                                            fontSize = 14.sp,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}