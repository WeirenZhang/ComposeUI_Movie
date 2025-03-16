package com.weiren.zhang.movie_composeui.model.theaterlist

import com.weiren.zhang.movie_composeui.datasource.local.entity.TheaterMyFavouriteEntity
import kotlinx.serialization.Serializable

@Serializable
data class TheaterInfoModel(
    val id: String,
    val name: String,
    val adds: String,
    val tel: String
) {
    fun toTheaterMyFavouriteEntity(): TheaterMyFavouriteEntity {
        return TheaterMyFavouriteEntity(
            id = id,
            name = name,
            adds = adds,
            tel = tel
        )
    }
}
