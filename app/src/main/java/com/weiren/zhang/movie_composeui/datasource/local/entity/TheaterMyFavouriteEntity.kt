package com.weiren.zhang.movie_composeui.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.weiren.zhang.movie_composeui.model.theaterlist.TheaterInfoModel
import com.weiren.zhang.movie_composeui.util.TheaterMyFavourite_TABLE

@Entity(
    tableName = TheaterMyFavourite_TABLE,
    indices = [Index("id", unique = true)]
)
data class TheaterMyFavouriteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "auto_id") val auto_id: Long = 0,
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "adds") val adds: String,
    @ColumnInfo(name = "tel") val tel: String,
    @ColumnInfo(name = "created_at") val createdAt: Long = System.currentTimeMillis()
) {
    fun toTheaterMyFavourite(): TheaterInfoModel {
        return TheaterInfoModel(
            id = id,
            name = name,
            adds = adds,
            tel = tel
        )
    }
}

