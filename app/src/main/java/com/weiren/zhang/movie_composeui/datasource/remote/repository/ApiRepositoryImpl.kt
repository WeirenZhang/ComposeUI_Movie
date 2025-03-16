package com.weiren.zhang.movie_composeui.datasource.remote.repository

import com.weiren.zhang.movie_composeui.model.movieinfomain.MovieDateTabItemModel
import com.weiren.zhang.movie_composeui.datasource.remote.api.Api
import com.weiren.zhang.movie_composeui.util.Resource
import com.weiren.zhang.movie_composeui.util.invokeApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepositoryImpl @Inject constructor(
    private val api: Api
) : ApiRepository {

    override suspend fun getMovieDateResult(
        movie_id: String,
        type: String
    ): Resource<List<MovieDateTabItemModel>> {

        val response = invokeApi {
            api.getMovieDateResult(movie_id = movie_id, type = "MovieTime")
        }
        return when (response) {
            is Resource.Error -> Resource.Error(error = response.error)
            is Resource.Loading -> Resource.Loading
            is Resource.Success -> Resource.Success(
                data = response.data?.map { it.toMovieDateTabItemModel() }.orEmpty()
            )
        }
    }
}