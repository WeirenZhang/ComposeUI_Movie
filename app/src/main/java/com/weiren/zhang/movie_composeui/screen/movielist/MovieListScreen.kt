package com.weiren.zhang.movie_composeui.screen.movielist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.weiren.zhang.movie_composeui.viewmodel.movielist.MovieListViewModel
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.weiren.zhang.movie_composeui.R
import com.weiren.zhang.movie_composeui.component.NoResults
import com.weiren.zhang.movie_composeui.model.movielist.MovieListModel
import com.weiren.zhang.movie_composeui.component.loadMoreView
import com.weiren.zhang.movie_composeui.component.movielist.MovieListItemWidget

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel = hiltViewModel(),
    onNavigateToMovieInfoMain: (model: MovieListModel) -> Unit,
    onBackClick: () -> Unit = {}
) {

    val homeMode by viewModel.homeMode.collectAsStateWithLifecycle()

    val lazyPagingItems = viewModel.pageFlow.collectAsLazyPagingItems()
    val pullRefreshState =
        rememberPullRefreshState(refreshing = viewModel.refreshing.value, onRefresh = {
            lazyPagingItems.refresh()
        })

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(homeMode.title, maxLines = 1)
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.back)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState)
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    count = lazyPagingItems.itemCount,
                    key = lazyPagingItems.itemKey<MovieListModel>(),
                    contentType = lazyPagingItems.itemContentType<MovieListModel>(
                    )
                ) { index ->
                    val item = lazyPagingItems[index]
                    if (item != null) {
                        MovieListItemWidget(
                            onClick = onNavigateToMovieInfoMain,
                            item = item
                        )
                    }
                }
                loadMoreView(lazyPagingItems)
            }
            PullRefreshIndicator(
                refreshing = viewModel.refreshing.value,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}