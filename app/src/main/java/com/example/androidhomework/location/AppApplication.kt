package com.example.androidhomework.location

import android.app.Application
import android.os.StrictMode
import com.jakewharton.threetenabp.AndroidThreeTen


class AppApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .permitNetwork()
                .build()

        )
    }
}