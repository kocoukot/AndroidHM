package com.example.androidhomework.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION.SDK
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat

object NotificationChannels {

    val HIGH_PRIORITY_CHANNEL_ID = "high_priority"
    val LOW_PRIORITY_CHANNEL_ID = "LOW_priority"


    fun create(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createHighPriorityChannel(context)
            createLowPriorityChannel(context)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createHighPriorityChannel(context: Context) {

        val name = "High priority"
        val channelDescription = "High priority channel"
        val priority = NotificationManager.IMPORTANCE_HIGH

        val channel = NotificationChannel(HIGH_PRIORITY_CHANNEL_ID, name, priority).apply {
            description = channelDescription
            setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE), null)
        }

        NotificationManagerCompat.from(context).createNotificationChannel(channel)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createLowPriorityChannel(context: Context) {

        val name = "Low priority"
        val channelDescription = "Low priority channel"
        val priority = NotificationManager.IMPORTANCE_LOW

        val channel = NotificationChannel(LOW_PRIORITY_CHANNEL_ID, name, priority).apply {
            description = channelDescription
            setSound(Uri.EMPTY, null)
        }

        NotificationManagerCompat.from(context).createNotificationChannel(channel)

    }
}