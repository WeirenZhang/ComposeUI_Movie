package com.weiren.zhang.movie_composeui.datasource.remote.repository

import com.weiren.zhang.movie_composeui.model.movieinfomain.MovieDateTabItemModel
import com.weiren.zhang.movie_composeui.util.Resource

interface ApiRepository {

    suspend fun getMovieDateResult(
        movie_id: String,
        type: String
    ): Resource<List<MovieDateTabItemModel>>
}