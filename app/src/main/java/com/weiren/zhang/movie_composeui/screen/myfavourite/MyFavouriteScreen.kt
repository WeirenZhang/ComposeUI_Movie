package com.weiren.zhang.movie_composeui.screen.myfavourite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.weiren.zhang.movie_composeui.R
import com.weiren.zhang.movie_composeui.model.movielist.MovieListModel
import com.weiren.zhang.movie_composeui.model.theaterlist.TheaterInfoModel
import com.weiren.zhang.movie_composeui.navigation.Screen
import com.weiren.zhang.movie_composeui.navigation.Screen1NavHost
import com.weiren.zhang.movie_composeui.viewmodel.myfavourite.MyFavouriteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyFavouriteScreen(
    viewModel: MyFavouriteViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    onNavigateToTheaterResult: (model: TheaterInfoModel) -> Unit,
    onNavigateToMovieInfoMain: (model: MovieListModel) -> Unit,
    onBackClick: () -> Unit = {}
) {
    val homeMode by viewModel.homeMode.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ), title = {
                Text(homeMode.title, maxLines = 1)
            }, navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.back)
                    )
                }
            })
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Screen1NavHost(
                navController = navController,
                onNavigateToMovieInfoMain = onNavigateToMovieInfoMain,
                onNavigateToTheaterResult = onNavigateToTheaterResult
            )
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {

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
                    navController.navigate(item.route) {
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
    val route: Screen,
) {
    MovieInfo(
        title = "電影",
        icon = R.drawable.ic_baseline_movie_filter_24,
        route = Screen.MovieMyFavourite,
    ),
    StoreInfo(
        title = "電影院",
        icon = R.drawable.ic_baseline_local_movies_24,
        route = Screen.TheaterMyFavouriten,
    )
}
