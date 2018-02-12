package com.lethe2211.voicealarm.presentation

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.media.AudioManager
import android.R.raw
import android.net.Uri
import com.lethe2211.voicealarm.R
import java.io.IOException
import android.support.annotation.RawRes




class PlaySoundService : Service() {

    lateinit var mediaPlayer: MediaPlayer
    val volume = 0.3f

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mediaPlayer = MediaPlayer()
        play()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        stop()
    }

    private fun play() {
        try {
            mediaPlayer.reset()
            mediaPlayer.setDataSource(this, getVideoUri(R.raw.sample))
            mediaPlayer.setVolume(volume, volume)
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM)
            mediaPlayer.prepare()
            mediaPlayer.start()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    private fun stop() {
        mediaPlayer.stop()
        mediaPlayer.release()
    }

    private fun getVideoUri(@RawRes resId: Int): Uri {
        return Uri.parse("android.resource://$packageName/$resId")
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}
