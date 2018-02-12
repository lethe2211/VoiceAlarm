package com.lethe2211.voicealarm.presentation

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CompoundButton
import android.widget.Switch
import com.lethe2211.voicealarm.data.AlarmItem
import com.lethe2211.voicealarm.R
import java.lang.reflect.Array.setLong
import android.app.AlarmManager
import android.os.Build
import android.app.PendingIntent
import android.content.Intent
import android.widget.Toast
import java.nio.file.Files.delete
import java.util.*


/**
 * Created by shuhei.shogen on 2018/02/05.
 */
class ListViewAdapter(private val context: Context) : BaseAdapter() { // TODO: Should extend ArrayAdapter

    var alarmItems: List<AlarmItem> = emptyList()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        if (convertView == null) {
            var alarmItemView: AlarmItemView = AlarmItemView(context)
            alarmItemView.setAlarmItem(alarmItems[position])
            convertView = alarmItemView
        }
        val switch = convertView.findViewById<Switch>(R.id.alarm_enabled) as Switch
        Log.d(this.javaClass.toString(), "switch")
        switch.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {

            override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
                if (isChecked) {
                    Log.d(this.javaClass.toString(), "enabled")
                    Toast.makeText(context as MainActivity, "enabled", Toast.LENGTH_LONG).show()


                    val calendar = Calendar.getInstance()
                    calendar.add(Calendar.SECOND, 5)
                    Log.d(this.javaClass.toString(), calendar.timeInMillis.toString())
                    register(context, calendar.timeInMillis)

                } else {
                    Log.d(this.javaClass.toString(), "disabled")
                    Toast.makeText(context, "disabled", Toast.LENGTH_LONG).show()
                }
            }

        })
        return convertView
    }

    override fun getItem(position: Int): Any? = alarmItems[position]

    override fun getItemId(position: Int): Long = 0

    override fun getCount(): Int = alarmItems.size

    private fun register(context: Context, alarmTimeMillis: Long) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val pendingIntent = getPendingIntent(context)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            alarmManager.setAlarmClock(AlarmManager.AlarmClockInfo(alarmTimeMillis, null), pendingIntent)
            Log.d(this.javaClass.toString(), "setAlarmClock")
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmTimeMillis, pendingIntent)
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, alarmTimeMillis, pendingIntent)
        }
        Log.d(this.javaClass.toString(), "register")

    }

    private fun unregister(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(getPendingIntent(context))
    }

    private fun getPendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, AlarmReceiver::class.java)
        /// intent.setClass(context, AlarmReceiver::class.java)
        // 複数のアラームを登録する場合はPendingIntent.getBroadcastの第二引数を変更する
        // 第二引数が同じで第四引数にFLAG_CANCEL_CURRENTがセットされている場合、2回以上呼び出されたときは
        // あとからのものが上書きされる
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)
    }
}