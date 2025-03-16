package com.weiren.zhang.movie_composeui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weiren.zhang.movie_composeui.R
import com.weiren.zhang.movie_composeui.model.HomeModel
import com.weiren.zhang.movie_composeui.model.movieinfomain.VideoModel
import com.weiren.zhang.movie_composeui.ui.theme.MovieIcons

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreviewMainScreen()
{
    val homeList = listOf(
        HomeModel(MovieIcons.enl_2, "本周新片", "0"),
        HomeModel(MovieIcons.enl_1, "本期首輪", "1"),
        HomeModel(MovieIcons.enl_1, "本期二輪", "2"),
        HomeModel(MovieIcons.enl_4, "近期上映", "3"),
        HomeModel(MovieIcons.enl_4, "新片快報", "4"),
        HomeModel(MovieIcons.enl_5, "電影院", "5"),
        HomeModel(MovieIcons.enl_3, "我的最愛", "6"),
        HomeModel(MovieIcons.enl_6, "網路訂票", "7")
    )
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("電影時刻表", maxLines = 1)
                }
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize().padding(top = 15.dp), columns = GridCells.Fixed(3),
            contentPadding = innerPadding
        ) {

            items(homeList) { item ->

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 10.dp),
                        painter = painterResource(id = R.drawable.enl_1),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                    )
                    Text(
                        text = "本周新片",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onNavigateToMovieList: (model: HomeModel) -> Unit,
    onNavigateToTheaterArea: (model: HomeModel) -> Unit,
    onNavigateToWebView: (model: VideoModel) -> Unit,
    onNavigateToMyFavourite: (model: HomeModel) -> Unit,
) {
    val homeList = listOf(
        HomeModel(MovieIcons.enl_2, "本周新片", "0"),
        HomeModel(MovieIcons.enl_1, "本期首輪", "1"),
        HomeModel(MovieIcons.enl_1, "本期二輪", "2"),
        HomeModel(MovieIcons.enl_4, "近期上映", "3"),
        HomeModel(MovieIcons.enl_4, "新片快報", "4"),
        HomeModel(MovieIcons.enl_5, "電影院", "5"),
        HomeModel(MovieIcons.enl_3, "我的最愛", "6"),
        HomeModel(MovieIcons.enl_6, "網路訂票", "7")
    )
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("電影時刻表", maxLines = 1)
                }
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize().padding(top = 15.dp), columns = GridCells.Fixed(3),
            contentPadding = innerPadding
        ) {

            items(homeList) { item ->

                when (item.tab.toInt()) {
                    in 0..4 -> HomeItemWidget(
                        homeModel = item,
                        onClick = onNavigateToMovieList,
                        onClick1 = onNavigateToWebView,
                        isToWebView = false
                    )

                    5 -> HomeItemWidget(
                        homeModel = item,
                        onClick = onNavigateToTheaterArea,
                        onClick1 = onNavigateToWebView,
                        isToWebView = false
                    )

                    6 -> HomeItemWidget(
                        homeModel = item,
                        onClick = onNavigateToMyFavourite,
                        onClick1 = onNavigateToWebView,
                        isToWebView = false
                    )

                    7 -> HomeItemWidget(
                        homeModel = item,
                        onClick = onNavigateToMyFavourite,
                        onClick1 = onNavigateToWebView,
                        isToWebView = true
                    )

                    else -> { // Note the block

                    }
                }
            }
        }
    }
}

@Composable
fun HomeItemWidget(
    modifier: Modifier = Modifier,
    homeModel: HomeModel,
    onClick: (model: HomeModel) -> Unit,
    onClick1: (model: VideoModel) -> Unit,
    isToWebView: Boolean
) {
    Column(
        modifier = modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            role = Role.Button,
            onClick = {
                if (isToWebView) {
                    onClick1(
                        VideoModel(
                            homeModel.title,
                            "https://www.ezding.com.tw/faq?comeFromApp=true&device=app",
                            ""
                        )
                    )
                } else {
                    onClick(homeModel)
                }
            }
        ), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 10.dp),
            painter = painterResource(homeModel.icon),
            contentDescription = null,
            contentScale = ContentScale.Fit,
        )
        Text(
            text = homeModel.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color.Black,
            style = MaterialTheme.typography.bodySmall,
            fontSize = 16.sp
        )
    }
}

