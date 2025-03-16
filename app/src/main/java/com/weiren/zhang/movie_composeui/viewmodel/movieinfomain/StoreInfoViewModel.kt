package com.weiren.zhang.movie_composeui.viewmodel.movieinfomain

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weiren.zhang.movie_composeui.datasource.remote.api.Api
import com.weiren.zhang.movie_composeui.model.movieinfomain.StoreInfoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreInfoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val api: Api
) : ViewModel() {

    private val movie_id = savedStateHandle.get<String>("movie_id")
    val StoreInfoList = mutableStateListOf<StoreInfoModel>()
    var refreshing = mutableStateOf(false)

    init {
        println(" StoreInfoViewModel $movie_id");
        getStoreInfoList()
    }

    fun getStoreInfoList() {
        viewModelScope.launch {
            runCatching {
                refreshing.value = true
                StoreInfoList.clear()
                StoreInfoList.addAll(api.getStoreInfo(movie_id = movie_id.toString(), type = "StoreInfo"))
                refreshing.value = false
            }.onFailure {
                refreshing.value = false
            }
        }
    }
}