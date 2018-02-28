package com.lethe2211.voicealarm.presentation

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import com.github.gfx.android.orma.AccessThreadConstraint
import com.github.gfx.android.orma.OrmaDatabaseBuilderBase
import com.lethe2211.voicealarm.data.AlarmItem
import com.lethe2211.voicealarm.R
import com.lethe2211.voicealarm.data.AlarmItemTable
import com.lethe2211.voicealarm.data.OrmaDatabase
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (intent.getBooleanExtra("fromNotification", false)) {
            Log.d(this::class.java.toString(), "fromNotification")
            stopService(Intent(this, PlaySoundService::class.java))
            stopService(Intent(this, PlaySoundService::class.java))
        }
    }

    override fun onResume() {

        // TODO: It should be a singleton
        val orma = OrmaDatabase.builder(this)
                .name("alarm.db")
                .readOnMainThread(AccessThreadConstraint.NONE) // FIXME: Maybe we shouldn't use main thread to access DB
                .writeOnMainThread(AccessThreadConstraint.NONE)
                .build()

        val alarmItemTableList: List<AlarmItem> = orma.selectFromAlarmItemTable()
                .map { it.toAlarmItem() }
                .toList()

        val listViewAdapter = ListViewAdapter(this)
        listViewAdapter.alarmItems = alarmItemTableList

        val listView = findViewById<ListView>(R.id.list_view) as ListView
        listView.adapter = listViewAdapter
//        listView.onItemClickListener = OnItemClickListener()

        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        Log.d(this.javaClass.toString(), "button clicked")

        when (item?.itemId) {
            R.id.add_button -> {
                Log.d(this.javaClass.toString(), "button clicked2")
                val intent = Intent(this, AlarmDetailActivity::class.java)
                startActivity(intent)

            }
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }
//    private inner class ListAdapter(context: Context, resources: Int) : ArrayAdapter<AlarmItem>(context, resources) {
//
//        override fun getItemView(position: Int, convertView: View?, parent: ViewGroup): View {
//            var convertView = convertView
//            if (convertView == null) {
//                // If there are no available views, we need to create it
//                convertView = layoutInflater.inflate(R.layout.list_item, null)
//            }
//
//            return convertView!!
//        }
//    }
//
//    private inner class OnItemClickListener : AdapterView.OnItemClickListener {
//        override fun onItemClick(adapterView: AdapterView<*>?, itemView: View?, position: Int, id: Long) {
//            Log.d(this.javaClass.toString(), "Clicked")
//            when (itemView?.id) {
//                R.id.alarm_enabled -> Log.d(this.javaClass.toString(), "enabled/disabled")
//                else -> Log.d(this.javaClass.toString(), "Other places clicked")
//            }
//        }
//    }

}
