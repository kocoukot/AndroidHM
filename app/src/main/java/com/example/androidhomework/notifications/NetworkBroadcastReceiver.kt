package com.example.androidhomework.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.androidhomework.R

class NetworkBroadcastReceiver : BroadcastReceiver() {

    var hasNet = true

    override fun onReceive(context: Context?, intent: Intent?) {
        context ?: return
        val connection = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo= connection.activeNetworkInfo
        if (networkInfo == null) {
            hasNet = false
            Log.d("Module29","Connect to the internet" )
        } else {
            hasNet = true
        }
    }
}