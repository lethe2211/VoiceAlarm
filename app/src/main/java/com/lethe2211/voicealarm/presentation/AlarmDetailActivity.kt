package com.lethe2211.voicealarm.presentation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.github.gfx.android.orma.AccessThreadConstraint
import com.lethe2211.voicealarm.R
import com.lethe2211.voicealarm.data.AlarmItemTable
import com.lethe2211.voicealarm.data.OrmaDatabase

class AlarmDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_detail)

        // We cannot use actionBar in case we use AppCompatActivity
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_alarm_detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
            R.id.save_button -> {
                val toast = Toast.makeText(this, "Saved!!", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()

                // TODO: It should be a singleton
                val orma = OrmaDatabase.builder(this)
                        .name("alarm.db")
                        .readOnMainThread(AccessThreadConstraint.NONE) // FIXME: Maybe we shouldn't use main thread to access DB
                        .writeOnMainThread(AccessThreadConstraint.NONE)
                        .build()
                orma.insertIntoAlarmItemTable(AlarmItemTable(1, "77:77", false))

                NavUtils.navigateUpFromSameTask(this)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }
}
