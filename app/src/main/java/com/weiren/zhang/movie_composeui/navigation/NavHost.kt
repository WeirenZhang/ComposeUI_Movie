package com.weiren.zhang.movie_composeui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.weiren.zhang.movie_composeui.model.HomeModel
import com.weiren.zhang.movie_composeui.model.movieinfomain.VideoModel
import com.weiren.zhang.movie_composeui.model.movielist.MovieListModel
import com.weiren.zhang.movie_composeui.model.theaterlist.TheaterInfoModel
import com.weiren.zhang.movie_composeui.screen.MainScreen
import com.weiren.zhang.movie_composeui.screen.WebViewScreen
import com.weiren.zhang.movie_composeui.screen.movieinfomain.BottomNavigation
import com.weiren.zhang.movie_composeui.screen.movieinfomain.MovieInfoMainScreen
import com.weiren.zhang.movie_composeui.screen.movieinfomain.MovieInfoScreen
import com.weiren.zhang.movie_composeui.screen.movieinfomain.MovieTimeResultScreen
import com.weiren.zhang.movie_composeui.screen.movieinfomain.StoreInfoScreen
import com.weiren.zhang.movie_composeui.screen.movieinfomain.VideoScreen
import com.weiren.zhang.movie_composeui.screen.movielist.MovieListScreen
import com.weiren.zhang.movie_composeui.screen.myfavourite.MovieMyFavouriteScreen
import com.weiren.zhang.movie_composeui.screen.myfavourite.MyFavouriteScreen
import com.weiren.zhang.movie_composeui.screen.myfavourite.TheaterMyFavouriteScreen
import com.weiren.zhang.movie_composeui.screen.theaterlist.TheaterAreaScreen
import com.weiren.zhang.movie_composeui.screen.theaterlist.TheaterListScreen
import com.weiren.zhang.movie_composeui.screen.theaterlist.TheaterResultScreen

@Composable
fun RouteNavHost(
    movie_id: String,
    navController: NavHostController,
    onNavigateToTheaterResult: (model: TheaterInfoModel) -> Unit,
    onNavigateToWebView: (model: VideoModel) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigation.MovieInfo.route + "?movie_id=" + movie_id
    ) {
        composable(
            BottomNavigation.MovieInfo.route + "?movie_id={movie_id}",
            arguments = listOf(
                navArgument(name = "movie_id") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {

            MovieInfoScreen()
        }
        composable(BottomNavigation.StoreInfo.route + "?movie_id={movie_id}",
            arguments = listOf(
                navArgument(name = "movie_id") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {

            StoreInfoScreen()
        }
        composable(BottomNavigation.MovieTimeTabs.route + "?movie_id={movie_id}",
            arguments = listOf(
                navArgument(name = "movie_id") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {

            MovieTimeResultScreen(
                onNavigateToTheaterResult = onNavigateToTheaterResult,
            )
        }
        composable(BottomNavigation.Video.route + "?movie_id={movie_id}",
            arguments = listOf(
                navArgument(name = "movie_id") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {

            VideoScreen(
                onNavigateToWebView = onNavigateToWebView,
            )
        }
    }
}

@Composable
fun ScreenNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onNavigateToMovieList: (model: HomeModel) -> Unit,
    onNavigateToMovieInfoMain: (model: MovieListModel) -> Unit,
    onNavigateToTheaterArea: (model: HomeModel) -> Unit,
    onNavigateToTheaterList: (name: String, model: List<TheaterInfoModel>) -> Unit,
    onNavigateToTheaterResult: (model: TheaterInfoModel) -> Unit,
    onNavigateToWebView: (model: VideoModel) -> Unit,
    onNavigateToMyFavourite: (model: HomeModel) -> Unit,
    onBackClick: () -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Main,
    ) {
        composable<Screen.Main> {
            MainScreen(
                onNavigateToMovieList = onNavigateToMovieList,
                onNavigateToTheaterArea = onNavigateToTheaterArea,
                onNavigateToWebView = onNavigateToWebView,
                onNavigateToMyFavourite = onNavigateToMyFavourite
            )
        }
        composable<Screen.MovieList>(
            typeMap = Screen.MovieList.typeMap
        ) {
            MovieListScreen(
                onNavigateToMovieInfoMain = onNavigateToMovieInfoMain,
                onBackClick = onBackClick
            )
        }
        composable<Screen.TheaterArea>(
            typeMap = Screen.TheaterArea.typeMap
        ) {
            TheaterAreaScreen(
                onNavigateToTheaterList = onNavigateToTheaterList,
                onBackClick = onBackClick
            )
        }
        composable<Screen.TheaterList>(
            typeMap = Screen.TheaterList.typeMap
        ) {
            TheaterListScreen(
                onNavigateToTheaterResult = onNavigateToTheaterResult,
                onBackClick = onBackClick
            )
        }
        composable<Screen.TheaterResult>(
            typeMap = Screen.TheaterResult.typeMap
        ) {
            TheaterResultScreen(
                onNavigateToMovieInfoMain = onNavigateToMovieInfoMain,
                onBackClick = onBackClick
            )
        }
        composable<Screen.MovieInfoMain>(
            typeMap = Screen.MovieInfoMain.typeMap
        ) {
            MovieInfoMainScreen(
                onNavigateToTheaterResult = onNavigateToTheaterResult,
                onNavigateToWebView = onNavigateToWebView,
                onBackClick = onBackClick
            )
        }
        composable<Screen.MyFavourite>(
            typeMap = Screen.MyFavourite.typeMap
        ) {
            MyFavouriteScreen(
                onNavigateToTheaterResult = onNavigateToTheaterResult,
                onNavigateToMovieInfoMain = onNavigateToMovieInfoMain,
                onBackClick = onBackClick
            )
        }
        composable<Screen.WebView>(
            typeMap = Screen.WebView.typeMap
        ) {
            WebViewScreen(
                onBackClick = onBackClick
            )
        }
    }
}

@Composable
fun Screen1NavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onNavigateToMovieInfoMain: (model: MovieListModel) -> Unit,
    onNavigateToTheaterResult: (model: TheaterInfoModel) -> Unit
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.MovieMyFavourite,
    ) {
        composable<Screen.MovieMyFavourite> {
            MovieMyFavouriteScreen(
                onNavigateToMovieInfoMain = onNavigateToMovieInfoMain
            )
        }
        composable<Screen.TheaterMyFavouriten> {
            TheaterMyFavouriteScreen(
                onNavigateToTheaterResult = onNavigateToTheaterResult
            )
        }
    }
}
