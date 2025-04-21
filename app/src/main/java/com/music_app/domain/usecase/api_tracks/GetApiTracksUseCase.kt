package com.music_app.domain.usecase.api_tracks

import com.music_app.domain.mapper.DomainToUiMapper
import com.music_app.domain.repository.ApiTracksRepository
import com.music_app.ui.models.TrackVO
import javax.inject.Inject

class GetApiTracksUseCase @Inject constructor(
    private val tracksRepository: ApiTracksRepository,
    private val domainToUiMapper: DomainToUiMapper
) {
    suspend fun getTracks(): Result<List<TrackVO>> {
        return tracksRepository.getTracks()
            .map { tracks -> tracks.map { domainToUiMapper.run { it.toViewObject() } } }
    }
}