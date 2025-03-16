package com.weiren.zhang.movie_composeui.viewmodel.movieinfomain

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weiren.zhang.movie_composeui.datasource.remote.api.Api
import com.weiren.zhang.movie_composeui.model.movieinfomain.MovieInfoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieInfoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val api: Api
) : ViewModel() {

    private val movie_id = savedStateHandle.get<String>("movie_id")
    val movieInfoList = mutableStateListOf<MovieInfoModel>()
    var refreshing = mutableStateOf(false)

    init {
        println(" MovieInfoViewModel $movie_id");
        getMovieInfoList()
    }

    fun getMovieInfoList() {
        viewModelScope.launch {
            runCatching {
                refreshing.value = true
                movieInfoList.clear()
                movieInfoList.addAll(api.getMovieInfo(movie_id = movie_id.toString(), type = "MovieInfo"))
                refreshing.value = false
            }.onFailure {
                refreshing.value = false
            }
        }
    }
}