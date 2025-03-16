package com.weiren.zhang.movie_composeui.screen.myfavourite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.weiren.zhang.movie_composeui.component.LoadingIndicator
import com.weiren.zhang.movie_composeui.component.NoResults
import com.weiren.zhang.movie_composeui.component.StateHandler
import com.weiren.zhang.movie_composeui.component.myfavourite.MovieMyFavouriteItemWidget
import com.weiren.zhang.movie_composeui.model.movielist.MovieListModel
import com.weiren.zhang.movie_composeui.viewmodel.myfavourite.MovieMyFavouriteViewModel

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MovieMyFavouriteScreen(
    viewModel: MovieMyFavouriteViewModel = hiltViewModel(),
    onNavigateToMovieInfoMain: (model: MovieListModel) -> Unit
) {

    val movieMyFavouriteList = viewModel.movieMyFavouriteList.collectAsState()

    StateHandler(state = movieMyFavouriteList.value, onLoading = {
        LoadingIndicator()
    }, onFailure = {}) { resource ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                resource.data?.let { movieMyFavouriteList ->
                    items(movieMyFavouriteList) { item ->
                        MovieMyFavouriteItemWidget(
                            onClick = onNavigateToMovieInfoMain,
                            item = item
                        )
                    }
                }
            }
            if (resource.data?.isEmpty() == true) {
                NoResults(
                    modifier = Modifier.fillMaxSize(),
                    text = "暫時無資訊"
                )
            }
        }
    }
}