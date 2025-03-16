package com.weiren.zhang.movie_composeui.viewmodel.movielist

import androidx.lifecycle.SavedStateHandle
import com.weiren.zhang.movie_composeui.datasource.remote.api.Api
import com.weiren.zhang.movie_composeui.model.movielist.MovieListModel
import com.weiren.zhang.movie_composeui.navigation.Screen
import com.weiren.zhang.movie_composeui.viewmodel.PagingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val api: Api
) : PagingViewModel<MovieListModel>() {

    private val homeMap = Screen.MovieList.from(savedStateHandle)

    private val _homeMap = MutableStateFlow(homeMap.homeModel)
    val homeMode = _homeMap.asStateFlow()

    override suspend fun doLoadPage(pageKey: String?): MutableList<MovieListModel> {
        return api.getMovieList(
            page = pageKey.toString(),
            type = "MovieList",
            tab = homeMode.value.tab
        )
    }
}