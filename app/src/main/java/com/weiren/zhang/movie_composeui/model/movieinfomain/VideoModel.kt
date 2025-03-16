package com.weiren.zhang.movie_composeui.model.movieinfomain
import kotlinx.serialization.Serializable

@Serializable
data class VideoModel(
    val title: String,
    val href: String,
    val cover: String,
)

