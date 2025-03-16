package com.weiren.zhang.movie_composeui.viewmodel.myfavourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weiren.zhang.movie_composeui.datasource.local.repository.RoomRepository
import com.weiren.zhang.movie_composeui.model.theaterlist.TheaterInfoModel
import com.weiren.zhang.movie_composeui.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TheaterMyFavouriteViewModel @Inject constructor(
    private val roomRepository: RoomRepository
) : ViewModel() {

    private val _theaterMyFavouriteList: MutableStateFlow<Resource<List<TheaterInfoModel>>> =
        MutableStateFlow(value = Resource.Loading)
    val theaterMyFavouriteList: StateFlow<Resource<List<TheaterInfoModel>>>
        get() = _theaterMyFavouriteList

    init {
        getAllTheaterMyFavourites()
    }

    private fun getAllTheaterMyFavourites() {
        viewModelScope.launch {
            roomRepository.getAllTheaterMyFavourites().collect { favourites ->
                _theaterMyFavouriteList.value = Resource.Success(favourites)
            }
        }
    }

    fun removeTheaterMyFavourite(id: String) {
        viewModelScope.launch {
            roomRepository.removeTheaterMyFavourite(id)
        }
    }
}