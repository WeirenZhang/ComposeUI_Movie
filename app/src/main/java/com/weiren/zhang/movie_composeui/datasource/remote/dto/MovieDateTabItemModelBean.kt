package com.weiren.zhang.movie_composeui.datasource.remote.dto

import com.weiren.zhang.movie_composeui.model.movieinfomain.MovieDateTabItemModel
import com.weiren.zhang.movie_composeui.model.movieinfomain.MovieTimeTabItemModel

data class MovieDateTabItemModelBean(
    val date: String,
    val list: List<MovieTimeTabItemModel>
) {
    fun toMovieDateTabItemModel(): MovieDateTabItemModel {
        return MovieDateTabItemModel(
            date = date,
            list = list
        )
    }
}