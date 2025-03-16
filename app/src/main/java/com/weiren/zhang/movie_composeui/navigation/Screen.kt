package com.weiren.zhang.movie_composeui.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.weiren.zhang.movie_composeui.util.serializableListType
import com.weiren.zhang.movie_composeui.util.serializableType
import com.weiren.zhang.movie_composeui.model.HomeModel
import com.weiren.zhang.movie_composeui.model.movieinfomain.VideoModel
import com.weiren.zhang.movie_composeui.model.movielist.MovieListModel
import com.weiren.zhang.movie_composeui.model.theaterlist.TheaterInfoModel
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
sealed class Screen {

    @Serializable
    object Main : Screen()

    @Serializable
    data class MovieList(val homeModel: HomeModel) : Screen() {
        companion object {
            val typeMap = mapOf(typeOf<HomeModel>() to serializableType<HomeModel>())

            fun from(savedStateHandle: SavedStateHandle) =
                savedStateHandle.toRoute<MovieList>(typeMap)
        }
    }

    @Serializable
    data class MovieInfoMain(val movieListModel: MovieListModel) : Screen() {
        companion object {
            val typeMap = mapOf(typeOf<MovieListModel>() to serializableType<MovieListModel>())

            fun from(savedStateHandle: SavedStateHandle) =
                savedStateHandle.toRoute<MovieInfoMain>(typeMap)
        }
    }

    @Serializable
    data class TheaterArea(val homeModel: HomeModel) : Screen() {
        companion object {
            val typeMap = mapOf(typeOf<HomeModel>() to serializableType<HomeModel>())

            fun from(savedStateHandle: SavedStateHandle) =
                savedStateHandle.toRoute<TheaterArea>(typeMap)
        }
    }

    @Serializable
    data class TheaterList(val name: String, val theaterInfoListModel: List<TheaterInfoModel>) :
        Screen() {
        companion object {
            val typeMap =
                mapOf(typeOf<List<TheaterInfoModel>>() to serializableListType<List<TheaterInfoModel>>())

            fun from(savedStateHandle: SavedStateHandle) =
                savedStateHandle.toRoute<TheaterList>(typeMap)
        }
    }

    @Serializable
    data class TheaterResult(val theaterInfoModel: TheaterInfoModel) : Screen() {
        companion object {
            val typeMap = mapOf(typeOf<TheaterInfoModel>() to serializableType<TheaterInfoModel>())

            fun from(savedStateHandle: SavedStateHandle) =
                savedStateHandle.toRoute<TheaterResult>(typeMap)
        }
    }

    @Serializable
    data class WebView(val videoModel: VideoModel) : Screen() {
        companion object {
            val typeMap = mapOf(typeOf<VideoModel>() to serializableType<VideoModel>())

            fun from(savedStateHandle: SavedStateHandle) =
                savedStateHandle.toRoute<WebView>(typeMap)
        }
    }

    @Serializable
    data class MyFavourite(val homeModel: HomeModel) : Screen() {
        companion object {
            val typeMap = mapOf(typeOf<HomeModel>() to serializableType<HomeModel>())

            fun from(savedStateHandle: SavedStateHandle) =
                savedStateHandle.toRoute<MyFavourite>(typeMap)
        }
    }

    @Serializable
    object MovieMyFavourite : Screen()

    @Serializable
    object TheaterMyFavouriten : Screen()
}




