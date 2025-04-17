package com.music_app.domain.usecase.api_tracks

import android.util.Log
import com.music_app.domain.mapper.DomainToUiMapper
import com.music_app.domain.models.Track
import com.music_app.domain.repository.TracksRepository
import com.music_app.ui.models.TrackVO
import javax.inject.Inject

class GetTracksByQueryUseCase @Inject constructor(
    private val tracksRepository: TracksRepository,
    private val domainToUiMapper: DomainToUiMapper
) {
    suspend fun getTracksByQuery(query: String): Result<List<TrackVO>> {
        return tracksRepository.getTracksByQuery(query)
            .map { tracks -> tracks.map { domainToUiMapper.run { it.toViewObject() } } }
    }
}