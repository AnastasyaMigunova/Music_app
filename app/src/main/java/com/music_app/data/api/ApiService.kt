package com.music_app.data.api

import com.music_app.data.models.SearchTrackResponse
import com.music_app.data.models.TrackListResponse
import com.music_app.data.models.TrackResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("chart")
    suspend fun getTracks() : TrackListResponse

    @GET("search")
    suspend fun searchTracks(@Query("q") query: String): SearchTrackResponse

    @GET("track/{id}")
    suspend fun getTrackById(@Path("id") id: Long): TrackResponse
}