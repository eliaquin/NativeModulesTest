package com.nativemodulestest

import android.app.*
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import android.os.Build
import android.graphics.Color
import android.content.Context

class AudioRecorderForegroundService : Service() {
    private lateinit var audioRecorderService: AudioRecorderService

    override fun onCreate() {
        super.onCreate()
        audioRecorderService = AudioRecorderService()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            "START_RECORDING" -> {
                val suffix = intent.getStringExtra("suffix") ?: "default"
                startForeground(NOTIFICATION_ID, createNotification())
                audioRecorderService.startRecording(applicationContext, suffix)
            }

            "STOP_RECORDING" -> {
                audioRecorderService.stopRecording()
                stopForeground(STOP_FOREGROUND_REMOVE)
                stopSelf()
            }
        }
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun createNotification(): Notification {
        val channelId = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        } else {
            ""
        }

        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("Audio Recording")
            .setContentText("Recording in progress")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
    }

    private fun createNotificationChannel(): String {
        val channelId = "AudioRecorderChannel"
        val channelName = "Audio Recorder Background Service"
        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH).apply {
            lightColor = Color.BLUE
            lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        }
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(channel)
        return channelId
    }

    companion object {
        private const val NOTIFICATION_ID = 1
    }
}