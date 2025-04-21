package com.music_app.data.di

import android.content.Context
import com.music_app.data.api.ApiService
import com.music_app.data.mapper.DataToDomainMapper
import com.music_app.data.repository.ApiTracksRepositoryImpl
import com.music_app.data.repository.SavedTracksRepositoryImpl
import com.music_app.data.tracksHelper.TracksHelper
import com.music_app.domain.repository.ApiTracksRepository
import com.music_app.domain.repository.SavedTracksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun provideApiTracksRepository(
        apiService: ApiService,
        dataToDomainMapper: DataToDomainMapper
    ): ApiTracksRepository {
        return ApiTracksRepositoryImpl(apiService, dataToDomainMapper)
    }

    @Provides
    fun provideSavedTracksRepository(
        @ApplicationContext context: Context,
        tracksHelper: TracksHelper
    ): SavedTracksRepository {
        return SavedTracksRepositoryImpl(context, tracksHelper)
    }

    @Provides
    fun provideTracksHelper(): TracksHelper { return TracksHelper() }
}