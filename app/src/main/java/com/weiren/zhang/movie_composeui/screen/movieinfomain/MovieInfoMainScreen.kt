package com.weiren.zhang.movie_composeui.screen.movieinfomain

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.weiren.zhang.movie_composeui.viewmodel.movieinfomain.MovieInfoMainViewModel
import androidx.navigation.compose.rememberNavController
import com.weiren.zhang.movie_composeui.R
import com.weiren.zhang.movie_composeui.model.movieinfomain.VideoModel
import com.weiren.zhang.movie_composeui.model.movielist.MovieListModel
import com.weiren.zhang.movie_composeui.model.theaterlist.TheaterInfoModel
import com.weiren.zhang.movie_composeui.navigation.RouteNavHost
import com.weiren.zhang.movie_composeui.R as Res

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieInfoMainScreen(
    viewModel: MovieInfoMainViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    onNavigateToTheaterResult: (model: TheaterInfoModel) -> Unit,
    onNavigateToWebView: (model: VideoModel) -> Unit,
    onBackClick: () -> Unit = {}
) {
    val movieListModel by viewModel.movieListModel.collectAsStateWithLifecycle()
    val isFavourite by viewModel.isMyFavourite.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ), title = {
                Text(movieListModel.title, maxLines = 1)
            }, navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.back)
                    )
                }
            },
                actions = {
                    Box {
                        MyFavouriteIcon(isFavourite, movieListModel, viewModel)
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                model = movieListModel
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            RouteNavHost(
                movie_id = movieListModel.id,
                navController = navController,
                onNavigateToTheaterResult,
                onNavigateToWebView
            )
        }
    }
}

@Composable
private fun MyFavouriteIcon(
    isFavourite: Boolean,
    model: MovieListModel,
    viewModel: MovieInfoMainViewModel,
) {
    val context = LocalContext.current
    val msg = stringResource(
        id = if (!isFavourite) Res.string.add_favourite else Res.string.remove_favourite
    )

    IconButton(
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(
                alpha = 0.45f
            )
        ),
        onClick = {
            val myFavourite = MovieListModel(
                title = model.title,
                en = model.en,
                release_movie_time = model.release_movie_time,
                thumb = model.thumb,
                id = model.id
            )
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            viewModel.myFavourite(myFavourite)
        }) {
        Icon(
            if (isFavourite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
            contentDescription = stringResource(id = Res.string.favourite),
            tint = MaterialTheme.colorScheme.primary,
        )
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController, model: MovieListModel) {

    var selectedItem by rememberSaveable { mutableStateOf(0) }

    NavigationBar {
        BottomNavigation.entries.forEachIndexed { index, item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = null
                    )
                },
                label = { Text(item.title) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(item.route + "?movie_id=" + model.id) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

enum class BottomNavigation(
    val title: String,
    val icon: Int,
    val route: String,
) {
    MovieInfo(
        title = "電影資料",
        icon = R.drawable.ic_baseline_movie_filter_24,
        route = "MovieInfo",
    ),
    StoreInfo(
        title = "劇情簡介",
        icon = R.drawable.ic_baseline_text_snippet_24,
        route = "StoreInfo",
    ),
    MovieTimeTabs(
        title = "播放時間",
        icon = R.drawable.ic_baseline_access_time_24,
        route = "MovieTimeTabs",
    ),
    Video(
        title = "預告片",
        icon = R.drawable.ic_baseline_videocam_24,
        route = "Video",
    )
}