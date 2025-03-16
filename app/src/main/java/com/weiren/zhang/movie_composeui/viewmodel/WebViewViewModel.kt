package com.weiren.zhang.movie_composeui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.weiren.zhang.movie_composeui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class WebViewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val videoMap = Screen.WebView.from(savedStateHandle)

    private val _videoMap = MutableStateFlow(videoMap.videoModel)
    val videoMode = _videoMap.asStateFlow()
}