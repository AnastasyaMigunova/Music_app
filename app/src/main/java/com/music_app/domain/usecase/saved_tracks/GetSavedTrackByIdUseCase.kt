package com.music_app.domain.usecase.saved_tracks

import com.music_app.domain.mapper.DomainToUiMapper
import com.music_app.domain.repository.SavedTracksRepository
import com.music_app.ui.models.TrackVO
import javax.inject.Inject

class GetSavedTrackByIdUseCase @Inject constructor(
    private val savedTracksRepository: SavedTracksRepository,
    private val domainToUiMapper: DomainToUiMapper
) {
    suspend fun getTrackById(id: Long): Result<TrackVO> {
        return savedTracksRepository.getTrackById(id)
            .map { domainToUiMapper.run { it.toViewObject() } }
    }
}