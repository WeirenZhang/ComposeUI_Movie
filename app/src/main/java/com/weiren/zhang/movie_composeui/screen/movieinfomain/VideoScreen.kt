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
import com.weiren.zhang.movie_composeui.component.NoResults
import com.weiren.zhang.movie_composeui.component.movieinfomain.VideoItemWidget
import com.weiren.zhang.movie_composeui.model.movieinfomain.VideoModel
import com.weiren.zhang.movie_composeui.viewmodel.movieinfomain.VideoViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun VideoScreen(
    viewModel: VideoViewModel = hiltViewModel(),
    onNavigateToWebView: (model: VideoModel) -> Unit,
) {

    val pullRefreshState =
        rememberPullRefreshState(refreshing = viewModel.refreshing.value, onRefresh = {
            viewModel.getVideoList()
        })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(viewModel.VideoList) { _, pagingItem ->
                VideoItemWidget(
                    item = pagingItem,
                    onClick = onNavigateToWebView
                )
            }
        }
        if (viewModel.VideoList.isEmpty() && !viewModel.refreshing.value) {
            NoResults(
                modifier = Modifier.fillMaxSize(),
                text = "暫時無資訊"
            )
        }
        PullRefreshIndicator(
            refreshing = viewModel.refreshing.value,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}