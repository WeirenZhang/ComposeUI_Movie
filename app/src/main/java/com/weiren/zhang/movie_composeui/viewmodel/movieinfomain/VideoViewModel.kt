package com.weiren.zhang.movie_composeui.viewmodel.movieinfomain

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weiren.zhang.movie_composeui.datasource.remote.api.Api
import com.weiren.zhang.movie_composeui.model.movieinfomain.VideoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val api: Api
) : ViewModel() {

    private val movie_id = savedStateHandle.get<String>("movie_id")
    val VideoList = mutableStateListOf<VideoModel>()
    var refreshing = mutableStateOf(false)

    init {
        println(" VideoViewModel $movie_id");
        getVideoList()
    }

    fun getVideoList() {
        viewModelScope.launch {
            runCatching {
                refreshing.value = true
                VideoList.clear()
                VideoList.addAll(api.getVideo(movie_id = movie_id.toString(), type = "Video"))
                refreshing.value = false
            }.onFailure {
                refreshing.value = false
            }
        }
    }
}