package com.music_app.data.tracksHelper

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import com.music_app.domain.models.Track

class TracksHelper {

    fun getAudioFiles(context: Context): List<Track> {
        val tracks = mutableListOf<Track>()

        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.DATA
        )

        val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"

        context.contentResolver.query(uri, projection, selection, null, null)?.use { cursor ->

            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val titleColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
            val artistColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
            val albumColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)
            val albumIdColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)
            val dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val title = cursor.getString(titleColumn)
                val duration = cursor.getInt(durationColumn)
                val artist = cursor.getString(artistColumn)
                val album = cursor.getString(albumColumn)
                val albumId = cursor.getLong(albumIdColumn)
                val data = cursor.getString(dataColumn)

                val albumArtUri = getAlbumArtUri(albumId)

                tracks.add(
                    Track(
                        id = id,
                        title = title,
                        previewUrl = data,
                        duration = duration,
                        artistName = artist ?: "Unknown Artist",
                        artistPicture = "",
                        albumTitle = album ?: "Unknown Album",
                        albumCover = albumArtUri.toString()
                    )
                )
            }
        }

        return tracks
    }

    private fun getAlbumArtUri(albumId: Long): Uri {
        return Uri.parse("content://media/external/audio/albumart").buildUpon()
            .appendPath(albumId.toString())
            .build()
    }
}