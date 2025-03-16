package com.weiren.zhang.movie_composeui.viewmodel.movieinfomain

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weiren.zhang.movie_composeui.datasource.remote.repository.ApiRepository
import com.weiren.zhang.movie_composeui.model.movieinfomain.MovieDateTabItemModel
import com.weiren.zhang.movie_composeui.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieTimeResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val api: ApiRepository
) : ViewModel() {

    private val movie_id = savedStateHandle.get<String>("movie_id")
    var selectedIndex = mutableStateOf(0)

    private val _MovieTimeResultDataMap: MutableStateFlow<Resource<List<MovieDateTabItemModel>>> =
        MutableStateFlow(value = Resource.Loading)
    val MovieTimeResultDataMap: StateFlow<Resource<List<MovieDateTabItemModel>>> get() = _MovieTimeResultDataMap

    init {
        println(" MovieTimeResultViewModel $movie_id");
        getMovieTimeResultList()
    }

    private fun getMovieTimeResultList() {
        viewModelScope.launch {
            runCatching {
                _MovieTimeResultDataMap.value =
                    api.getMovieDateResult(movie_id = movie_id.toString(), type = "MovieTime")
            }.onFailure {

            }
        }
    }
}