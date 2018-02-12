package com.lethe2211.voicealarm.presentation

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.widget.Toast
import com.lethe2211.voicealarm.R
import android.app.PendingIntent




/**
 * Created by shuhei.shogen on 2018/02/08.
 */
class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(this::class.java.toString(), "onReceive")
        Toast.makeText(context, "onReceive", Toast.LENGTH_LONG).show()

//        val startActivityIntent = Intent(context, PlaySoundActivity::class.java)
//        startActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        context.startActivity(startActivityIntent)

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channelId = "default";

        // FIXME: NotificationChannel requires API level 26
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                    channelId,
                    "Normal channel",
                    NotificationManager.IMPORTANCE_DEFAULT)
//        channel.setDescription(message);
//        channel.enableVibration(true);
//        channel.canShowBadge();
//        channel.enableLights(true);
//        channel.setLightColor(Color.BLUE);
//        // the channel appears on the lockscreen
//        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
//        channel.setSound(defaultSoundUri, null);
//        channel.setShowBadge(true);
            notificationManager.createNotificationChannel(channel)
        }

        val notificationIntent = Intent(context, MainActivity::class.java)

        notificationIntent.putExtra("fromNotification", true)

        // Delete all the activities related to this app
        notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP.or(Intent.FLAG_ACTIVITY_CLEAR_TASK).or(Intent.FLAG_ACTIVITY_NEW_TASK)

        val contentIntent = PendingIntent.getActivity(context, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(context, channelId)
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setContentTitle("This is title")
                .setContentTitle("This is message")
                .setSmallIcon(R.drawable.notification_icon_background)
                .setVibrate(longArrayOf(500, 500))
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .build()
        notificationManager.notify(1, notification)

        val playSoundServiceIntent = Intent(context, PlaySoundService::class.java)
        context.startService(playSoundServiceIntent)
    }

}