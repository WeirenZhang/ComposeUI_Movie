package com.weiren.zhang.movie_composeui.datasource.local.di

import android.app.Application
import androidx.room.Room
import com.weiren.zhang.movie_composeui.datasource.local.database.Database
import com.weiren.zhang.movie_composeui.datasource.local.repository.RoomRepository
import com.weiren.zhang.movie_composeui.datasource.local.repository.RoomRepositoryImpl
import com.weiren.zhang.movie_composeui.util.Movie_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides  // The Application binding is available without qualifiers.
    fun providesDatabase(application: Application): Database {
        return Room.databaseBuilder(
            application,
            Database::class.java,
            Movie_DATABASE,
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesRoomRepository(database: Database): RoomRepository {
        return RoomRepositoryImpl(database)
    }
}