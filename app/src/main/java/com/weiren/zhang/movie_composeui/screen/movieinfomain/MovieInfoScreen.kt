package com.weiren.zhang.movie_composeui.screen.movieinfomain

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.weiren.zhang.movie_composeui.component.movieinfomain.MovieInfoItemWidget
import com.weiren.zhang.movie_composeui.viewmodel.movieinfomain.MovieInfoViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieInfoScreen(
    viewModel: MovieInfoViewModel = hiltViewModel()
) {

    val pullRefreshState =
        rememberPullRefreshState(refreshing = viewModel.refreshing.value, onRefresh = {
            viewModel.getMovieInfoList()
        })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(viewModel.movieInfoList) { _, pagingItem ->
                MovieInfoItemWidget(
                    item = pagingItem
                )
            }
        }
        PullRefreshIndicator(
            refreshing = viewModel.refreshing.value,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}