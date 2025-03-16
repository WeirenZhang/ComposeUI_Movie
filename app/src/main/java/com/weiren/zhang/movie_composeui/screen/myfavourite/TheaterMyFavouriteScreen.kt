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
import com.weiren.zhang.movie_composeui.component.myfavourite.TheaterMyFavouriteItemWidget
import com.weiren.zhang.movie_composeui.model.theaterlist.TheaterInfoModel
import com.weiren.zhang.movie_composeui.viewmodel.myfavourite.TheaterMyFavouriteViewModel

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TheaterMyFavouriteScreen(
    viewModel: TheaterMyFavouriteViewModel = hiltViewModel(),
    onNavigateToTheaterResult: (model: TheaterInfoModel) -> Unit,
) {

    val theaterMyFavouriteList = viewModel.theaterMyFavouriteList.collectAsState()

    StateHandler(state = theaterMyFavouriteList.value, onLoading = {
        LoadingIndicator()
    }, onFailure = {}) { resource ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                resource.data?.let { theaterMyFavouriteList ->
                    items(theaterMyFavouriteList) { item ->
                        TheaterMyFavouriteItemWidget(
                            onClick = onNavigateToTheaterResult,
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