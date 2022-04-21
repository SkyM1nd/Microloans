package com.example.martynov

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MediaBroadcastService : Service() {
    companion object{

        const val ACTION_MEDIA_BROADCAST = "MediaBroadcastService"
        const val EXTRA_NAME = "extra_name"
        var nameSong = ""
    }

    private lateinit var mp: MediaPlayer
    override fun onBind(p0: Intent?): IBinder? {
        return Binder()
    }

    override fun onCreate() {
        mp = MediaPlayer.create(this, R.raw.mysound)
        mp.setOnCompletionListener { onCompletion() }

        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mp.start()

        val intent = Intent(ACTION_MEDIA_BROADCAST)
        intent.putExtra(EXTRA_NAME, "HammAli & Navai - Птичка")
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)

        nameSong = "HammAli & Navai - Птичка"

        return START_STICKY
    }

    override fun onDestroy() {
        val intent = Intent(ACTION_MEDIA_BROADCAST)
        intent.putExtra(EXTRA_NAME, "")
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)

        nameSong = ""

        mp.stop()
        super.onDestroy()
    }

    private fun onCompletion() {
        super.stopSelf()
    }
}