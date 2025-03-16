package com.weiren.zhang.movie_composeui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.weiren.zhang.movie_composeui.navigation.Screen
import com.weiren.zhang.movie_composeui.navigation.ScreenNavHost
import com.weiren.zhang.movie_composeui.ui.theme.Movie_ComposeUITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Movie_ComposeUITheme {
                App()
            }
        }
    }
}

@Composable
private fun App(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    ScreenNavHost(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        navController = navController,
        onNavigateToMovieList = { model ->
            navController.navigate(Screen.MovieList(model))
        },
        onNavigateToMovieInfoMain = { model ->
            navController.navigate(Screen.MovieInfoMain(model))
        },
        onNavigateToTheaterArea = { model ->
            navController.navigate(Screen.TheaterArea(model))
        },
        onNavigateToTheaterList = { name, model ->
            navController.navigate(Screen.TheaterList(name, model))
        },
        onNavigateToTheaterResult = { model ->
            navController.navigate(Screen.TheaterResult(model))
        },
        onNavigateToMyFavourite = { model ->
            navController.navigate(Screen.MyFavourite(model))
        },
        onNavigateToWebView = { model ->
            navController.navigate(Screen.WebView(model))
        },
        onBackClick = {
            navController.popBackStack()
        }
    )
}