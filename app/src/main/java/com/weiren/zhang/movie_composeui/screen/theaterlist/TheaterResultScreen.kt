package com.weiren.zhang.movie_composeui.screen.theaterlist

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.weiren.zhang.movie_composeui.R
import com.weiren.zhang.movie_composeui.component.NoResults
import com.weiren.zhang.movie_composeui.component.theaterlist.TheaterResultItemWidget
import com.weiren.zhang.movie_composeui.model.movielist.MovieListModel
import com.weiren.zhang.movie_composeui.model.theaterlist.TheaterInfoModel
import com.weiren.zhang.movie_composeui.viewmodel.theaterlist.TheaterResultViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun TheaterResultScreen(
    viewModel: TheaterResultViewModel = hiltViewModel(),
    onNavigateToMovieInfoMain: (model: MovieListModel) -> Unit,
    onBackClick: () -> Unit = {}
) {
    val theaterInfoModel by viewModel.theaterInfoMode.collectAsStateWithLifecycle()
    val isFavourite by viewModel.isMyFavourite.collectAsStateWithLifecycle()
    var selectedIndex by remember { mutableIntStateOf(0) }

    val pullRefreshState =
        rememberPullRefreshState(refreshing = viewModel.refreshing.value, onRefresh = {
            selectedIndex = 0
            viewModel.getTheaterResultList()
        })

    Scaffold(topBar = {
        TopAppBar(colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ), title = {
            Text(theaterInfoModel.name, maxLines = 1)
        }, navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.back)
                )
            }
        },
            actions = {
                Box {
                    MyFavouriteIcon(
                        isFavourite,
                        theaterInfoModel,
                        viewModel
                    )
                }
            }
        )
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState)
                .padding(innerPadding)
        ) {
            Column(Modifier.fillMaxSize()) {
                if (viewModel.theaterDateItem.size > 0) {

                    var expanded by remember { mutableStateOf(false) }
                    var selectedText by remember { mutableStateOf(viewModel.theaterDateItem[selectedIndex].date) }

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = {
                            expanded = !expanded
                        }
                    ) {
                        OutlinedTextField(
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.DateRange,
                                    contentDescription = ""
                                )
                            },
                            value = selectedText,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally)
                                .padding(10.dp)
                        )
                        DropdownMenu(
                            modifier = Modifier.exposedDropdownSize(),
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            viewModel.theaterDateItem.forEachIndexed { index, item ->
                                DropdownMenuItem(
                                    text = { Text(text = item.date) },
                                    onClick = {
                                        selectedIndex = index
                                        selectedText = item.date
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    if (viewModel.theaterDateItem.size > 0) {
                        itemsIndexed(viewModel.theaterDateItem[selectedIndex].data) { _, pagingItem ->
                            TheaterResultItemWidget(
                                onClick = onNavigateToMovieInfoMain,
                                item = pagingItem
                            )
                        }
                    }
                }
            }
            if (viewModel.theaterDateItem.isEmpty() && !viewModel.refreshing.value) {
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
}

@Composable
private fun MyFavouriteIcon(
    isFavourite: Boolean,
    model: TheaterInfoModel,
    viewModel: TheaterResultViewModel,
) {
    val context = LocalContext.current
    val msg = stringResource(
        id = if (!isFavourite) R.string.add_favourite else R.string.remove_favourite
    )

    IconButton(
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(
                alpha = 0.45f
            )
        ),
        onClick = {
            val myFavourite = TheaterInfoModel(
                id = model.id,
                name = model.name,
                adds = model.adds,
                tel = model.tel
            )
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            viewModel.myFavourite(myFavourite)
        }) {
        Icon(
            if (isFavourite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
            contentDescription = stringResource(id = R.string.favourite),
            tint = MaterialTheme.colorScheme.primary,
        )
    }
}