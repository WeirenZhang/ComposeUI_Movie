package com.weiren.zhang.movie_composeui.datasource.local.repository

import com.weiren.zhang.movie_composeui.model.movielist.MovieListModel
import com.weiren.zhang.movie_composeui.model.theaterlist.TheaterInfoModel
import kotlinx.coroutines.flow.Flow

interface RoomRepository {
    suspend fun getAllMovieMyFavourites(): Flow<List<MovieListModel>>

    suspend fun getAllTheaterMyFavourites(): Flow<List<TheaterInfoModel>>

    suspend fun addOrRemoveMovieMyFavourite(movieListModel: MovieListModel)

    suspend fun addOrRemoveTheaterMyFavourite(theaterInfoModel: TheaterInfoModel)

    suspend fun checkMovieMyFavourite(id: String): Flow<Boolean>

    suspend fun checkTheaterMyFavourite(id: String): Flow<Boolean>

    suspend fun removeMovieMyFavourite(id: String)

    suspend fun removeTheaterMyFavourite(id: String)
}