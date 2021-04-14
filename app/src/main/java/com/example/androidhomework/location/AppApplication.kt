package com.example.androidhomework.location

import android.app.Application
import android.os.StrictMode
import com.example.androidhomework.BuildConfig
import com.example.androidhomework.roomdao.data.bd.Database
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import com.jakewharton.threetenabp.AndroidThreeTen


class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .permitNetwork()
                .build())
        SoLoader.init(this, false)




        if (FlipperUtils.shouldEnableFlipper(this)) {
            val client = AndroidFlipperClient.getInstance(this)
            client.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
            client.start()
        }
        Database.init(this)
//
//        if ( FlipperUtils.shouldEnableFlipper(this)){
//            AndroidFlipperClient.getInstance(this)
//                .apply {
//                    addPlugin(InspectorFlipperPlugin(this@AppApplication, DescriptorMapping.withDefaults()))
//                 //   addPlugin(Network.flipperNetPlugging)
//                }.start()
//        }


    }
}