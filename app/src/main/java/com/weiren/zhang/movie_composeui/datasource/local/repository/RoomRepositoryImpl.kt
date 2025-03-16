package com.weiren.zhang.movie_composeui.datasource.local.repository

import com.weiren.zhang.movie_composeui.datasource.local.database.Database
import com.weiren.zhang.movie_composeui.model.movielist.MovieListModel
import com.weiren.zhang.movie_composeui.model.theaterlist.TheaterInfoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomRepositoryImpl @Inject constructor(
    private val database: Database
) : RoomRepository {
    private val myFavouriteDao = database.MyFavouriteDao()

    override suspend fun getAllMovieMyFavourites(): Flow<List<MovieListModel>> {
        return myFavouriteDao.getAllMovieMyFavourites()
            .map { it.map { movieMyFavouriteEntity -> movieMyFavouriteEntity.toMovieMyFavourite() } }
    }

    override suspend fun getAllTheaterMyFavourites(): Flow<List<TheaterInfoModel>> {
        return myFavouriteDao.getAllTheaterMyFavourites()
            .map { it.map { theaterMyFavouriteEntity -> theaterMyFavouriteEntity.toTheaterMyFavourite() } }
    }

    override suspend fun addOrRemoveMovieMyFavourite(movieListModel: MovieListModel) {
        val hasMyFavourite = checkMovieMyFavourite(movieListModel.id).first()
        if (hasMyFavourite) {
            myFavouriteDao.deleteMovieMyFavouriteById(movieListModel.id)
        } else {
            myFavouriteDao.insertMovieMyFavourite(movieListModel.toMovieMyFavouriteEntity())
        }
    }

    override suspend fun addOrRemoveTheaterMyFavourite(theaterInfoModel: TheaterInfoModel) {
        val hasMyFavourite = checkTheaterMyFavourite(theaterInfoModel.id).first()
        if (hasMyFavourite) {
            myFavouriteDao.deleteTheaterMyFavouriteById(theaterInfoModel.id)
        } else {
            myFavouriteDao.insertTheaterMyFavourite(theaterInfoModel.toTheaterMyFavouriteEntity())
        }
    }

    override suspend fun checkMovieMyFavourite(id: String): Flow<Boolean> {
        return flow {
            myFavouriteDao.checkMovieMyFavouriteById(id).collect {
                emit(value = it != null)
            }
        }
    }

    override suspend fun checkTheaterMyFavourite(id: String): Flow<Boolean> {
        return flow {
            myFavouriteDao.checkTheaterMyFavouriteById(id).collect {
                emit(value = it != null)
            }
        }
    }

    override suspend fun removeMovieMyFavourite(id: String) {
        myFavouriteDao.deleteMovieMyFavouriteById(id)
    }

    override suspend fun removeTheaterMyFavourite(id: String) {
        myFavouriteDao.deleteTheaterMyFavouriteById(id)
    }
}