package com.weiren.zhang.movie_composeui.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.weiren.zhang.movie_composeui.model.movielist.MovieListModel
import com.weiren.zhang.movie_composeui.util.MovieMyFavourite_TABLE

@Entity(
    tableName = MovieMyFavourite_TABLE,
    indices = [Index("id", unique = true)]
)
data class MovieMyFavouriteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "auto_id") val auto_id: Long = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "en") val en: String,
    @ColumnInfo(name = "release_movie_time") val release_movie_time: String,
    @ColumnInfo(name = "thumb") val thumb: String,
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "created_at") val createdAt: Long = System.currentTimeMillis()
) {
    fun toMovieMyFavourite(): MovieListModel {
        return MovieListModel(
            title = title,
            en = en,
            release_movie_time = release_movie_time,
            thumb = thumb,
            id = id,
        )
    }
}