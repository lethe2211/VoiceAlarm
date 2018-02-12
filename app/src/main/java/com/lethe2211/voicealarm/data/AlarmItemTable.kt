package com.lethe2211.voicealarm.data

import com.github.gfx.android.orma.annotation.Column
import com.github.gfx.android.orma.annotation.PrimaryKey
import com.github.gfx.android.orma.annotation.Table

/**
 * Created by shuhei.shogen on 2018/02/07.
 */
@Table("alarm_item")
class AlarmItemTable {

    // TODO: Use lateinit instead of initialization
    @PrimaryKey(autoincrement = true)
    var id: Long = 0

    @Column(value = "alarm_time")
    var alarmTime: String = "--:--"

    @Column(value = "enabled")
    var enabled: Boolean = false

    constructor()

    constructor(id: Long, alarmTime: String, enabled: Boolean) {
        this.id = id
        this.alarmTime = alarmTime
        this.enabled = enabled
    }

    fun toAlarmItem(): AlarmItem {
        return AlarmItem(alarmTime, enabled)
    }

}