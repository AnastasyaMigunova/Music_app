package com.music_app.data.media_service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.session.MediaSession
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.music_app.MainActivity
import com.music_app.R
import com.music_app.domain.models.Track
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicPlayerService : Service() {

    private lateinit var exoPlayer: ExoPlayer
    private lateinit var mediaSession: MediaSession
    private var currentTrack: Track? = null

    override fun onCreate() {
        super.onCreate()

        exoPlayer = ExoPlayer.Builder(this).build()

        mediaSession = MediaSession(this, "MusicPlayerService")

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationChannelId = "music_channel"
        val notificationChannel = NotificationChannel(notificationChannelId, "Music Notifications", NotificationManager.IMPORTANCE_LOW)
        notificationManager.createNotificationChannel(notificationChannel)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val track = intent?.getParcelableExtra<Track>("track")

        if (track != null) {
            playTrack(track)
        }

        return START_NOT_STICKY
    }

    private fun playTrack(track: Track) {
        val mediaItem = MediaItem.fromUri(track.previewUrl)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.play()

        currentTrack = track

        startForeground(1, createNotification(track))
    }

    private fun createNotification(track: Track): Notification {
        val playPauseAction = if (exoPlayer.isPlaying) {
            createPauseAction()
        } else {
            createPlayAction()
        }

        return NotificationCompat.Builder(this, "music_channel")
            .setContentTitle(track.title)
            .setContentText(track.artistName)
            .setContentIntent(getContentIntent())
            .addAction(playPauseAction)
            .setOngoing(true)
            .build()
    }

    private fun createPlayAction(): NotificationCompat.Action {
        val playIntent = Intent(this, MusicPlayerService::class.java)
        playIntent.putExtra("action", "play")
        val playPendingIntent = PendingIntent.getService(
            this,
            0,
            playIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Action(R.drawable.ic_play, "Play", playPendingIntent)
    }

    private fun createPauseAction(): NotificationCompat.Action {
        val pauseIntent = Intent(this, MusicPlayerService::class.java)
        pauseIntent.putExtra("action", "pause")
        val pausePendingIntent = PendingIntent.getService(
            this,
            0,
            pauseIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Action(R.drawable.ic_pause, "Pause", pausePendingIntent)
    }

    private fun getContentIntent(): PendingIntent {
        val intent = Intent(this, MainActivity::class.java)
        return PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer.release()
    }
}