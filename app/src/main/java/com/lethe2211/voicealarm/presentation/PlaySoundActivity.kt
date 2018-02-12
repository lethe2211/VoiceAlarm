package com.lethe2211.voicealarm.presentation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.lethe2211.voicealarm.R

class PlaySoundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(this::class.java.toString(), "onCreate")
        Toast.makeText(this, "onCreate", Toast.LENGTH_LONG)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_sound)
    }
}
