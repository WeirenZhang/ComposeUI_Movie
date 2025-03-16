package com.weiren.zhang.movie_composeui.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.weiren.zhang.movie_composeui.datasource.local.dao.MyFavouriteDao
import com.weiren.zhang.movie_composeui.datasource.local.entity.MovieMyFavouriteEntity
import com.weiren.zhang.movie_composeui.datasource.local.entity.TheaterMyFavouriteEntity

@Database(
    version = 1,
    entities = [
        MovieMyFavouriteEntity::class,
        TheaterMyFavouriteEntity::class
    ]
)
abstract class Database : RoomDatabase() {
    abstract fun MyFavouriteDao(): MyFavouriteDao
}


