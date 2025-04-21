package com.music_app.domain.usecase.api_tracks

import com.music_app.domain.mapper.DomainToUiMapper
import com.music_app.domain.repository.ApiTracksRepository
import com.music_app.ui.models.TrackVO
import javax.inject.Inject

class GetApiTrackByIdUseCase @Inject constructor(
    private val tracksRepository: ApiTracksRepository,
    private val domainToUiMapper: DomainToUiMapper
) {
    suspend fun getTrackById(id: Long): Result<TrackVO> {
        return tracksRepository.getTrackById(id)
            .map { domainToUiMapper.run { it.toViewObject() } }
    }
}