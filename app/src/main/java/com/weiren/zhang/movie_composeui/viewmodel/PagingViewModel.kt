package com.weiren.zhang.movie_composeui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.delay

abstract class PagingViewModel<T : Any> : ViewModel() {

    var refreshing = mutableStateOf(false)
    val pageFlow = Pager(config = PagingConfig(pageSize = 10,
        initialLoadSize = 10,
        prefetchDistance = 1), pagingSourceFactory = {
        ListPagingSource()
    }).flow.cachedIn(viewModelScope)

    inner class ListPagingSource() : PagingSource<Int, T>() {

        var page = 1
        override fun getRefreshKey(state: PagingState<Int, T>): Int? = null
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
            return try {
                val pageKey = params.key ?: page
                val apiResult = if (pageKey == 1) {
                    refreshing.value = true
                    doLoadPage(pageKey.toString())
                } else {
                    doLoadPage(pageKey.toString())
                }
                refreshing.value = false
                LoadResult.Page(
                    apiResult,
                    null,
                    nextKey = if (apiResult.isEmpty()) null else pageKey + 1
                )
            } catch (e: Exception) {
                delay(200)
                refreshing.value = false
                //errorToast(e.message ?: "")
                println(("error:" + e.message) ?: "");
                LoadResult.Error(e)
            }
        }
    }

    abstract suspend fun doLoadPage(pageKey: String? = null): MutableList<T>
}