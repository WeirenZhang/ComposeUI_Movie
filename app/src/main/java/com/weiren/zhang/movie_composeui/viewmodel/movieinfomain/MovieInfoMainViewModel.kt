package com.weiren.zhang.movie_composeui.viewmodel.movieinfomain

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weiren.zhang.movie_composeui.datasource.local.repository.RoomRepository
import com.weiren.zhang.movie_composeui.model.movielist.MovieListModel
import com.weiren.zhang.movie_composeui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieInfoMainViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val roomRepository: RoomRepository,
) : ViewModel() {

    private val movieListMap = Screen.MovieInfoMain.from(savedStateHandle)

    private val _movieListModel = MutableStateFlow(movieListMap.movieListModel)
    val movieListModel = _movieListModel.asStateFlow()

    private val _isMyFavourite: MutableStateFlow<Boolean> =
        MutableStateFlow(value = false)
    val isMyFavourite: StateFlow<Boolean>
        get() = _isMyFavourite

    init {
        viewModelScope.launch {
            _isMyFavourite.value =
                roomRepository.checkMovieMyFavourite(movieListModel.value.id).first()
        }
    }

    fun myFavourite(movieListModel: MovieListModel) {
        viewModelScope.launch {
            _isMyFavourite.value = !_isMyFavourite.value
            roomRepository.addOrRemoveMovieMyFavourite(movieListModel)
        }
    }
}