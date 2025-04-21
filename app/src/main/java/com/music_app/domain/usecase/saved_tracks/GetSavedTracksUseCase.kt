package com.music_app.domain.usecase.saved_tracks

import com.music_app.domain.mapper.DomainToUiMapper
import com.music_app.domain.repository.SavedTracksRepository
import com.music_app.ui.models.TrackVO
import javax.inject.Inject

class GetSavedTracksUseCase @Inject constructor(
    private val savedTracksRepository: SavedTracksRepository,
    private val domainToUiMapper: DomainToUiMapper
) {
    suspend fun getTracks(): Result<List<TrackVO>> {
        return savedTracksRepository.getTracks()
            .map { tracks -> tracks.map { domainToUiMapper.run { it.toViewObject() } } }
    }
}