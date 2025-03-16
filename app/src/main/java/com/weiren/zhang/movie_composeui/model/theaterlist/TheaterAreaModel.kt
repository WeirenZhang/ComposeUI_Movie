package com.weiren.zhang.movie_composeui.model.theaterlist

import kotlinx.serialization.Serializable

@Serializable
data class TheaterAreaModel(
    val theater_top: String,
    val theater_list: List<TheaterInfoModel>
)

