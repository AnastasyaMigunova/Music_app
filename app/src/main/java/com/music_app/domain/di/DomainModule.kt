package com.music_app.domain.di

import com.music_app.data.repository.ApiTracksRepositoryImpl
import com.music_app.data.repository.SavedTracksRepositoryImpl
import com.music_app.domain.mapper.DomainToUiMapper
import com.music_app.domain.usecase.api_tracks.GetApiTrackByIdUseCase
import com.music_app.domain.usecase.api_tracks.GetApiTracksByQueryUseCase
import com.music_app.domain.usecase.api_tracks.GetApiTracksUseCase
import com.music_app.domain.usecase.saved_tracks.GetSavedTrackByIdUseCase
import com.music_app.domain.usecase.saved_tracks.GetSavedTracksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {
    @Provides
    fun provideGetApiTracksUseCase(
        tracksRepositoryImpl: ApiTracksRepositoryImpl,
        domainToUiMapper: DomainToUiMapper
    ): GetApiTracksUseCase {
        return GetApiTracksUseCase(tracksRepositoryImpl, domainToUiMapper)
    }

    @Provides
    fun provideGetApiTrackByIdUseCase(
        tracksRepositoryImpl: ApiTracksRepositoryImpl,
        domainToUiMapper: DomainToUiMapper
    ): GetApiTrackByIdUseCase {
        return GetApiTrackByIdUseCase(tracksRepositoryImpl, domainToUiMapper)
    }

    @Provides
    fun provideGetApiTracksByQueryUseCase(
        tracksRepositoryImpl: ApiTracksRepositoryImpl,
        domainToUiMapper: DomainToUiMapper
    ): GetApiTracksByQueryUseCase {
        return GetApiTracksByQueryUseCase(tracksRepositoryImpl, domainToUiMapper)
    }

    @Provides
    fun provideGetSavedTracksUseCase(
        savedTracksRepositoryImpl: SavedTracksRepositoryImpl,
        domainToUiMapper: DomainToUiMapper
    ): GetSavedTracksUseCase {
        return GetSavedTracksUseCase(savedTracksRepositoryImpl, domainToUiMapper)
    }

    @Provides
    fun provideGetSavedTrackByIdUseCase(
        savedTracksRepositoryImpl: SavedTracksRepositoryImpl,
        domainToUiMapper: DomainToUiMapper
    ): GetSavedTrackByIdUseCase {
        return GetSavedTrackByIdUseCase(savedTracksRepositoryImpl, domainToUiMapper)
    }
}