package com.weiren.zhang.movie_composeui.model

import kotlinx.serialization.Serializable

@Serializable
data class HomeModel (
    val icon: Int,
    val title: String,
    val tab: String
)