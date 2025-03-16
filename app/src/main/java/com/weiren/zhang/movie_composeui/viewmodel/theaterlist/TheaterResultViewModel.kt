package com.weiren.zhang.movie_composeui.viewmodel.theaterlist

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weiren.zhang.movie_composeui.datasource.local.repository.RoomRepository
import com.weiren.zhang.movie_composeui.datasource.remote.api.Api
import com.weiren.zhang.movie_composeui.model.theaterlist.TheaterDateItemModel
import com.weiren.zhang.movie_composeui.model.theaterlist.TheaterInfoModel
import com.weiren.zhang.movie_composeui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TheaterResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle, private val api: Api,
    private val roomRepository: RoomRepository,
) : ViewModel() {

    private val theaterInfoMap = Screen.TheaterResult.from(savedStateHandle)

    private val _theaterInfoMap = MutableStateFlow(theaterInfoMap.theaterInfoModel)
    val theaterInfoMode = _theaterInfoMap.asStateFlow()

    val theaterDateItem = mutableStateListOf<TheaterDateItemModel>()
    var refreshing = mutableStateOf(false)

    private val _isMyFavourite: MutableStateFlow<Boolean> =
        MutableStateFlow(value = false)
    val isMyFavourite: StateFlow<Boolean>
        get() = _isMyFavourite

    init {
        viewModelScope.launch {
            _isMyFavourite.value =
                roomRepository.checkTheaterMyFavourite(theaterInfoMode.value.id).first()
        }
        getTheaterResultList()
    }

    fun myFavourite(theaterInfoModel: TheaterInfoModel) {
        viewModelScope.launch {
            _isMyFavourite.value = !_isMyFavourite.value
            roomRepository.addOrRemoveTheaterMyFavourite(theaterInfoModel)
        }
    }

    fun getTheaterResultList() {
        viewModelScope.launch {
            runCatching {
                refreshing.value = true
                theaterDateItem.clear()
                theaterDateItem.addAll(
                    api.getTheaterResultList(
                        type = "TheaterResult", id = theaterInfoMode.value.id
                    )
                )
                refreshing.value = false
            }.onFailure {
                refreshing.value = false
            }
        }
    }
}