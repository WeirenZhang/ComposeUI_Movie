package com.weiren.zhang.movie_composeui.viewmodel.myfavourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weiren.zhang.movie_composeui.datasource.local.repository.RoomRepository
import com.weiren.zhang.movie_composeui.model.movielist.MovieListModel
import com.weiren.zhang.movie_composeui.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieMyFavouriteViewModel @Inject constructor(
    private val roomRepository: RoomRepository
) : ViewModel() {

    private val _movieMyFavouriteList: MutableStateFlow<Resource<List<MovieListModel>>> =
        MutableStateFlow(value = Resource.Loading)
    val movieMyFavouriteList: StateFlow<Resource<List<MovieListModel>>>
        get() = _movieMyFavouriteList

    init {
        getAllMovieMyFavourites()
    }

    private fun getAllMovieMyFavourites() {
        viewModelScope.launch {
            roomRepository.getAllMovieMyFavourites().collect { favourites ->
                _movieMyFavouriteList.value = Resource.Success(favourites)
            }
        }
    }

    fun removeMovieMyFavourite(id: String) {
        viewModelScope.launch {
            roomRepository.removeMovieMyFavourite(id)
        }
    }
}