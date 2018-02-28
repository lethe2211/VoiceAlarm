package com.lethe2211.voicealarm.presentation

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.media.AudioManager
import android.net.Uri
import com.lethe2211.voicealarm.R
import java.io.IOException
import android.support.annotation.RawRes




class PlaySoundService : Service() {

    lateinit var mediaPlayer: MediaPlayer
    val volume = 0.3f

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        init()
        play()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        terminate()
    }

    private fun init() {
        mediaPlayer.reset()
        mediaPlayer.setDataSource(this, getVideoUri(R.raw.sample))
        mediaPlayer.setVolume(volume, volume)
        mediaPlayer.setOnCompletionListener { stopSelf() }
        mediaPlayer.prepare()
    }

    private fun play() {
        try {
            mediaPlayer.start()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    private fun terminate() {
        if (mediaPlayer != null) {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }

    private fun getVideoUri(@RawRes resId: Int): Uri {
        return Uri.parse("android.resource://$packageName/$resId")
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}
