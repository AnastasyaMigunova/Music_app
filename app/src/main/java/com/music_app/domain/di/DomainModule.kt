package com.music_app.domain.di

import com.music_app.data.repository.ApiTracksRepositoryImpl
import com.music_app.domain.mapper.DomainToUiMapper
import com.music_app.domain.usecase.api_tracks.GetApiTracksUseCase
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
}