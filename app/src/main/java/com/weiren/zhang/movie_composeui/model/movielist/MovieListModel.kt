package com.weiren.zhang.movie_composeui.model.movielist

import com.weiren.zhang.movie_composeui.datasource.local.entity.MovieMyFavouriteEntity
import kotlinx.serialization.Serializable

@Serializable
data class MovieListModel(
    val title: String,
    val en: String,
    val release_movie_time: String,
    val thumb: String,
    var id: String
) {
    fun toMovieMyFavouriteEntity(): MovieMyFavouriteEntity {
        return MovieMyFavouriteEntity(
            title = title,
            en = en,
            release_movie_time = release_movie_time,
            thumb = thumb,
            id = id,
        )
    }
}
