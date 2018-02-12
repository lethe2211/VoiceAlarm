package com.lethe2211.voicealarm.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.lethe2211.voicealarm.data.AlarmItem
import com.lethe2211.voicealarm.R

/**
 * Created by shuhei.shogen on 2018/02/05.
 */
class AlarmItemView : FrameLayout {

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    var alarmTime: TextView? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.list_item, this)
        alarmTime = findViewById<TextView>(R.id.alarm_time) as TextView
    }

    fun setAlarmItem(alarmItem: AlarmItem) {
        alarmTime?.text = alarmItem.alarmTime
    }


}