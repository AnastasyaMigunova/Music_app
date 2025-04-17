package com.music_app.domain.usecase.api_tracks

import android.util.Log
import com.music_app.domain.mapper.DomainToUiMapper
import com.music_app.domain.repository.TracksRepository
import com.music_app.ui.models.TrackVO
import javax.inject.Inject

class GetTrackByIdUseCase @Inject constructor(
    private val tracksRepository: TracksRepository,
    private val domainToUiMapper: DomainToUiMapper
) {
    suspend fun getTrackById(id: Long): Result<TrackVO> {
        return tracksRepository.getTrackById(id)
            .map { domainToUiMapper.run { it.toViewObject() } }
    }
}