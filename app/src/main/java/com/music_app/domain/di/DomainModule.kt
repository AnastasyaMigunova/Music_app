package com.music_app.domain.di

import com.music_app.data.repository.TracksRepositoryImpl
import com.music_app.domain.mapper.DomainToUiMapper
import com.music_app.domain.usecase.api_tracks.GetTracksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {
    @Provides
    fun provideGetTracksUseCase (
        tracksRepositoryImpl: TracksRepositoryImpl,
        domainToUiMapper: DomainToUiMapper
    ): GetTracksUseCase {
        return GetTracksUseCase(tracksRepositoryImpl, domainToUiMapper)
    }
}