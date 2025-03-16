package com.weiren.zhang.movie_composeui.viewmodel.theaterlist

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weiren.zhang.movie_composeui.datasource.remote.api.Api
import com.weiren.zhang.movie_composeui.model.theaterlist.TheaterAreaModel
import com.weiren.zhang.movie_composeui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TheaterAreaViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val api: Api
) : ViewModel() {

    private val homeMap = Screen.TheaterArea.from(savedStateHandle)

    private val _homeMap = MutableStateFlow(homeMap.homeModel)
    val homeMode = _homeMap.asStateFlow()

    val theaterAreaList = mutableStateListOf<TheaterAreaModel>()
    var refreshing = mutableStateOf(false)

    init {
        getTheaterAreaList()
    }

    fun getTheaterAreaList() {
        viewModelScope.launch {
            runCatching {
                refreshing.value = true
                theaterAreaList.clear()
                theaterAreaList.addAll(api.getTheaterList(type = "Area"))
                refreshing.value = false
            }.onFailure {
                refreshing.value = false
            }
        }
    }
}