package com.weiren.zhang.movie_composeui.model.theaterlist

import com.weiren.zhang.movie_composeui.model.TypesModel

data class TheaterDateItemModel(
    val date: String,
    val data: List<TheaterResultModel>
)

data class TheaterResultModel(
    val id: String,
    val release_foto: String,
    val theaterlist_name: String,
    val length: String,
    var icon: String,
    val types: List<TypesModel>
)
