package com.weiren.zhang.movie_composeui.component.movieinfomain

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weiren.zhang.movie_composeui.R
import com.weiren.zhang.movie_composeui.component.VerticalGrid
import com.weiren.zhang.movie_composeui.model.movieinfomain.MovieTimeResultModel
import com.weiren.zhang.movie_composeui.model.theaterlist.TheaterInfoModel

@Preview
@Composable
fun PreviewMovieTimeResultWidget() {

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Text(
                text = "獅子王：木法沙",
                modifier = Modifier
                    .padding(10.dp),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
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

                items(3) { item ->

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

@Composable
fun MovieTimeResultWidget(
    modifier: Modifier = Modifier,
    onClick: (model: TheaterInfoModel) -> Unit,
    tab: List<MovieTimeResultModel>,
) {

    LazyColumn() {
        items(tab.size) { index ->
            val item = tab[index]

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
                                TheaterInfoModel(
                                    item.id,
                                    item.theater,
                                    "",
                                    ""
                                )
                            )
                        },
                    )
                    .fillMaxWidth()
                    .padding(10.dp),
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Text(
                        text = item.theater,
                        modifier = modifier
                            .padding(10.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    repeat(item.types.size) { index ->

                        VerticalGrid(columnCount = 3) {
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

                        VerticalGrid(columnCount = 3) {
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


