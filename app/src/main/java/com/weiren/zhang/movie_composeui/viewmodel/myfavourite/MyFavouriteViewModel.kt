package com.weiren.zhang.movie_composeui.viewmodel.myfavourite

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.weiren.zhang.movie_composeui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MyFavouriteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val homeMap = Screen.MyFavourite.from(savedStateHandle)

    private val _homeMap = MutableStateFlow(homeMap.homeModel)
    val homeMode = _homeMap.asStateFlow()
}