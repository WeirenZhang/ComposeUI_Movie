package com.weiren.zhang.movie_composeui.screen.theaterlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.weiren.zhang.movie_composeui.R
import com.weiren.zhang.movie_composeui.model.theaterlist.TheaterInfoModel
import com.weiren.zhang.movie_composeui.component.theaterlist.TheaterListItemWidget
import com.weiren.zhang.movie_composeui.viewmodel.theaterlist.TheaterListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TheaterListScreen(
    viewModel: TheaterListViewModel = hiltViewModel(),
    onNavigateToTheaterResult: (model: TheaterInfoModel) -> Unit,
    onBackClick: () -> Unit = {}
) {
    val theaterInfoListModel by viewModel.theaterInfoListModel.collectAsStateWithLifecycle()
    val name by viewModel.name.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        TopAppBar(colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ), title = {
            Text(name, maxLines = 1)
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
                .padding(innerPadding)
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(theaterInfoListModel) { _, pagingItem ->
                    TheaterListItemWidget(
                        onClick = onNavigateToTheaterResult,
                        item = pagingItem
                    )
                }
            }
        }
    }
}