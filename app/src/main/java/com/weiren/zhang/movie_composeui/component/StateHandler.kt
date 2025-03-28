package com.weiren.zhang.movie_composeui.component

import androidx.compose.runtime.Composable
import com.weiren.zhang.movie_composeui.util.Resource

@Composable
fun <T> StateHandler(
    state: Resource<T>,
    onLoading: @Composable (Resource<T>) -> Unit,
    onFailure: @Composable (Resource<T>) -> Unit,
    onSuccess: @Composable (Resource<T>) -> Unit
) {

    if (state is Resource.Loading) {
        onLoading(state)
    }
    if (state is Resource.Error) {
        onFailure(state)
    }
    onSuccess(state)
}