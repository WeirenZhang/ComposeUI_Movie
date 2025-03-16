package com.weiren.zhang.movie_composeui.screen.movieinfomain

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.weiren.zhang.movie_composeui.component.LoadingIndicator
import com.weiren.zhang.movie_composeui.component.NoResults
import com.weiren.zhang.movie_composeui.component.StateHandler
import com.weiren.zhang.movie_composeui.component.movieinfomain.MovieTimeResultWidget
import com.weiren.zhang.movie_composeui.model.movieinfomain.MovieTimeTabItemModel
import com.weiren.zhang.movie_composeui.model.theaterlist.TheaterInfoModel
import com.weiren.zhang.movie_composeui.viewmodel.movieinfomain.MovieTimeResultViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieTimeResultScreen(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    viewModel: MovieTimeResultViewModel = hiltViewModel(),
    onNavigateToTheaterResult: (model: TheaterInfoModel) -> Unit,
) {

    val MovieTimeResultDataState by viewModel.MovieTimeResultDataMap.collectAsState()
    var selectedIndex by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(Modifier.fillMaxSize()) {

            StateHandler(
                state = MovieTimeResultDataState,
                onLoading = { LoadingIndicator() },
                onFailure = {

                }
            ) { resource ->
                resource.data?.let { MovieTimeResultDataMap ->

                    if (MovieTimeResultDataMap.isNotEmpty()) {

                        val pagerState = rememberPagerState(
                            pageCount = { MovieTimeResultDataMap[selectedIndex].list.size },
                            initialPage = 0
                        )

                        LaunchedEffect(pagerState.currentPage) {
                            viewModel.selectedIndex.value = pagerState.currentPage
                        }

                        var expanded by remember { mutableStateOf(false) }
                        var selectedText by remember { mutableStateOf(MovieTimeResultDataMap[selectedIndex].date) }

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
                                MovieTimeResultDataMap.forEachIndexed { index, item ->
                                    DropdownMenuItem(
                                        text = { Text(text = item.date) },
                                        onClick = {
                                            selectedText = item.date
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }
                        MovieTimeTabRowWidget(
                            MovieTimeResultDataMap[selectedIndex].list,
                            viewModel.selectedIndex.value
                        ) { index ->
                            coroutineScope.launch {
                                viewModel.selectedIndex.value = index
                                pagerState.animateScrollToPage(index)
                            }
                        }
                        MovieTimeTabPageWidget(
                            MovieTimeResultDataMap[selectedIndex].list,
                            pagerState,
                            modifier = Modifier,
                            onNavigateToTheaterResult
                        )

                    } else {
                        NoResults(
                            modifier = Modifier.fillMaxSize(),
                            text = "暫時無資訊"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MovieTimeTabRowWidget(
    tabs: List<MovieTimeTabItemModel>,
    selectedIndex: Int,
    onTabClick: (Int) -> Unit,
) {
    if (tabs.isEmpty()) {
        return
    }

    ScrollableTabRow(selectedTabIndex = selectedIndex,
        edgePadding = 10.dp,
        indicator = { tabPositions ->
            Box(
                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedIndex]),
                contentAlignment = Alignment.Center
            ) {
                HorizontalDivider(
                    modifier = Modifier.width(80.dp),
                    thickness = 3.dp,
                    color = LocalContentColor.current
                )
            }
        }) {
        tabs.forEachIndexed { index, tabInfo ->
            Tab(selected = selectedIndex == index, onClick = {
                onTabClick(index)
            }, text = {
                Text(text = tabInfo.area, fontSize = 14.sp)
            }, selectedContentColor = Color.Black, unselectedContentColor = Color.Black)
        }
    }
}

@Composable
fun MovieTimeTabPageWidget(
    tabs: List<MovieTimeTabItemModel>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    onNavigateToTheaterResult: (model: TheaterInfoModel) -> Unit,
) {
    HorizontalPager(state = pagerState, modifier = modifier) { pageIndex ->
        MovieTimeResultWidget(
            tab = tabs[pageIndex].data,
            onClick = onNavigateToTheaterResult,
        )
    }
}


