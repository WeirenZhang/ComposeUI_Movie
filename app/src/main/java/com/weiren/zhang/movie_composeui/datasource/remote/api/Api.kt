package com.weiren.zhang.movie_composeui.datasource.remote.api

import com.weiren.zhang.movie_composeui.datasource.remote.dto.MovieDateTabItemModelBean
import com.weiren.zhang.movie_composeui.model.movieinfomain.MovieInfoModel
import com.weiren.zhang.movie_composeui.model.movieinfomain.StoreInfoModel
import com.weiren.zhang.movie_composeui.model.movieinfomain.VideoModel
import com.weiren.zhang.movie_composeui.model.movielist.MovieListModel
import com.weiren.zhang.movie_composeui.model.theaterlist.TheaterAreaModel
import com.weiren.zhang.movie_composeui.model.theaterlist.TheaterDateItemModel
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    //movielist
    @GET("macros/s/AKfycbzNPN95_VIeYPTKF85yVS5oml_lUiVL0TUlQvuNj1krEUjUQFtBq_BY6eraap6zW2ZI/exec")
    suspend fun getMovieList(
        @Query("page") page: String,
        @Query("type") type: String,
        @Query("tab") tab: String
    ): MutableList<MovieListModel>

    //movieinfomain
    @GET("macros/s/AKfycbzNPN95_VIeYPTKF85yVS5oml_lUiVL0TUlQvuNj1krEUjUQFtBq_BY6eraap6zW2ZI/exec")
    suspend fun getMovieInfo(
        @Query("movie_id") movie_id: String,
        @Query("type") type: String
    ): List<MovieInfoModel>

    @GET("macros/s/AKfycbzNPN95_VIeYPTKF85yVS5oml_lUiVL0TUlQvuNj1krEUjUQFtBq_BY6eraap6zW2ZI/exec")
    suspend fun getStoreInfo(
        @Query("movie_id") movie_id: String,
        @Query("type") type: String
    ): List<StoreInfoModel>

    @GET("macros/s/AKfycbzNPN95_VIeYPTKF85yVS5oml_lUiVL0TUlQvuNj1krEUjUQFtBq_BY6eraap6zW2ZI/exec")
    suspend fun getMovieDateResult(
        @Query("movie_id") movie_id: String,
        @Query("type") type: String
    ): List<MovieDateTabItemModelBean>

    @GET("macros/s/AKfycbzNPN95_VIeYPTKF85yVS5oml_lUiVL0TUlQvuNj1krEUjUQFtBq_BY6eraap6zW2ZI/exec")
    suspend fun getVideo(
        @Query("movie_id") movie_id: String,
        @Query("type") type: String
    ): List<VideoModel>

    //theaterlist
    @GET("macros/s/AKfycbzNPN95_VIeYPTKF85yVS5oml_lUiVL0TUlQvuNj1krEUjUQFtBq_BY6eraap6zW2ZI/exec")
    suspend fun getTheaterList(@Query("type") type: String): List<TheaterAreaModel>

    @GET("macros/s/AKfycbzNPN95_VIeYPTKF85yVS5oml_lUiVL0TUlQvuNj1krEUjUQFtBq_BY6eraap6zW2ZI/exec")
    suspend fun getTheaterResultList(
        @Query("cinema_id") id: String,
        @Query("type") type: String
    ): List<TheaterDateItemModel>
}