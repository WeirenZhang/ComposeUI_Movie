package com.weiren.zhang.movie_composeui.screen.theaterlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.weiren.zhang.movie_composeui.viewmodel.theaterlist.TheaterAreaViewModel
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.weiren.zhang.movie_composeui.R
import com.weiren.zhang.movie_composeui.model.theaterlist.TheaterInfoModel
import com.weiren.zhang.movie_composeui.component.theaterlist.TheaterAreaItemWidget

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TheaterAreaScreen(
    viewModel: TheaterAreaViewModel = hiltViewModel(),
    onNavigateToTheaterList: (name: String, model: List<TheaterInfoModel>) -> Unit,
    onBackClick: () -> Unit = {}
) {

    val homeMode by viewModel.homeMode.collectAsStateWithLifecycle()

    val pullRefreshState =
        rememberPullRefreshState(refreshing = viewModel.refreshing.value, onRefresh = {
            viewModel.getTheaterAreaList()
        })

    Scaffold(topBar = {
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
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState)
                .padding(innerPadding)
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(viewModel.theaterAreaList) { _, pagingItem ->
                    TheaterAreaItemWidget(
                        onClick = onNavigateToTheaterList,
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
}