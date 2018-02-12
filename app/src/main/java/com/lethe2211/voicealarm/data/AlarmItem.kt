package com.lethe2211.voicealarm.data

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by shuhei.shogen on 2018/02/04.
 */
//data class AlarmItem(val alarmTime: String, val enabled: Boolean) : Parcelable {
//
//    companion object {
//
//        @JvmField
//        val CREATOR: Parcelable.Creator<AlarmItem> = object : Parcelable.Creator<AlarmItem> {
//            override fun createFromParcel(source: Parcel): AlarmItem = source.run {
//                AlarmItem(readString(), readByte() != 0.toByte())
//            }
//
//            override fun newArray(size: Int): Array<AlarmItem?> = arrayOfNulls(size)
//        }
//
//    }
//
//    override fun describeContents(): Int = 0
//
//    override fun writeToParcel(dest: Parcel, flags: Int) {
//        dest.run {
//            writeString(alarmTime)
//            writeByte((if (enabled) 1 else 0).toByte())
//        }
//    }
//}
data class AlarmItem(val alarmTime: String, val enabled: Boolean)