package com.weiren.zhang.movie_composeui.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.weiren.zhang.movie_composeui.datasource.local.entity.MovieMyFavouriteEntity
import com.weiren.zhang.movie_composeui.datasource.local.entity.TheaterMyFavouriteEntity
import com.weiren.zhang.movie_composeui.util.MovieMyFavourite_TABLE
import com.weiren.zhang.movie_composeui.util.TheaterMyFavourite_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface MyFavouriteDao {
    @Query("SELECT * FROM $MovieMyFavourite_TABLE ORDER BY created_at DESC")
    fun getAllMovieMyFavourites(): Flow<List<MovieMyFavouriteEntity>>

    @Query("SELECT * FROM $TheaterMyFavourite_TABLE ORDER BY created_at DESC")
    fun getAllTheaterMyFavourites(): Flow<List<TheaterMyFavouriteEntity>>

    @Query("SELECT * FROM $MovieMyFavourite_TABLE  WHERE id = :id")
    fun getMovieMyFavouriteById(id: String): Flow<MovieMyFavouriteEntity>

    @Query("SELECT * FROM $TheaterMyFavourite_TABLE  WHERE id = :id")
    fun getTheaterMyFavouriteById(id: String): Flow<TheaterMyFavouriteEntity>

    @Query("SELECT * FROM $MovieMyFavourite_TABLE  WHERE id = :id")
    fun checkMovieMyFavouriteById(id: String): Flow<MovieMyFavouriteEntity?>

    @Query("SELECT * FROM $TheaterMyFavourite_TABLE  WHERE id = :id")
    fun checkTheaterMyFavouriteById(id: String): Flow<TheaterMyFavouriteEntity?>

    @Query("DELETE FROM $MovieMyFavourite_TABLE  WHERE id = :id")
    suspend fun deleteMovieMyFavouriteById(id: String)

    @Query("DELETE FROM $TheaterMyFavourite_TABLE  WHERE id = :id")
    suspend fun deleteTheaterMyFavouriteById(id: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieMyFavourite(movieMyFavourite: MovieMyFavouriteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTheaterMyFavourite(theaterMyFavourite: TheaterMyFavouriteEntity)
}