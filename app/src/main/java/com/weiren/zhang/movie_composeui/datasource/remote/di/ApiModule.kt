package com.weiren.zhang.movie_composeui.datasource.remote.di

import com.weiren.zhang.movie_composeui.datasource.remote.repository.ApiRepositoryImpl
import com.weiren.zhang.movie_composeui.datasource.remote.repository.ApiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiModule {

    @Singleton
    @Binds
    abstract fun providesAnimeRepository(apiRepositoryImpl: ApiRepositoryImpl): ApiRepository
}