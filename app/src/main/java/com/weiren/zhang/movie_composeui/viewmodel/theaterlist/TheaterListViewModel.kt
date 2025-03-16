package com.weiren.zhang.movie_composeui.viewmodel.theaterlist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.weiren.zhang.movie_composeui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TheaterListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val theaterInfoListMap = Screen.TheaterList.from(savedStateHandle)

    private val _theaterInfoListModel = MutableStateFlow(theaterInfoListMap.theaterInfoListModel)
    val theaterInfoListModel = _theaterInfoListModel.asStateFlow()

    private val _name = MutableStateFlow(theaterInfoListMap.name)
    val name = _name.asStateFlow()
}