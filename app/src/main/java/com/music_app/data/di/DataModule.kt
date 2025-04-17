package com.music_app.data.di

import com.music_app.data.api.ApiService
import com.music_app.data.mapper.DataToDomainMapper
import com.music_app.data.repository.TracksRepositoryImpl
import com.music_app.domain.repository.TracksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun provideTracksRepository(
        apiService: ApiService,
        dataToDomainMapper: DataToDomainMapper
    ): TracksRepository {
        return TracksRepositoryImpl(apiService, dataToDomainMapper)
    }

}