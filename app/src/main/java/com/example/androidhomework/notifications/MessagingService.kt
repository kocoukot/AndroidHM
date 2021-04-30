package com.example.androidhomework.notifications

import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bumptech.glide.Glide
import com.example.androidhomework.MainActivity
import com.example.androidhomework.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.android.synthetic.main.item_video.*

class MessagingService : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val isChat = message.data["isChat"]?.toBoolean()
        if (isChat == true) {
            val userName = message.data["userName"]
            val messageText = message.data["text"]
            if (userName != null && messageText != null) {
                showMessageNotify(userName, messageText)
            }
        } else {
            val title = message.data["title"]
            val description = message.data["description"]
            val imageUrl = message.data["imageUrl"]
            if (title != null && description != null) {
                showSaleNotification(title, description, imageUrl)
            }
        }
    }

    private fun showMessageNotify(user: String, message: String) {
        val intent = Intent(this, MainActivity::class.java)
        val largeIcon = BitmapFactory.decodeResource(resources, R.drawable.image_first)

        val pendingIntent = PendingIntent.getActivity(this, 123, intent, 0)
        val notification =
            NotificationCompat.Builder(this, NotificationChannels.HIGH_PRIORITY_CHANNEL_ID)
                .setContentTitle("Message from $user")
                .setContentText("Message $message")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSmallIcon(R.drawable.ic_message)
                .setAutoCancel(true)
                .setLargeIcon(largeIcon)
                .setContentIntent(pendingIntent)
                .build()
        NotificationManagerCompat.from(this).notify(12334, notification)
    }


    private fun showSaleNotification(title: String, description: String, url: String?) {
        val intent = Intent(this, MainActivity::class.java)

        val largeIcon = BitmapFactory.decodeResource(resources, R.drawable.ic_baseline_star)
        val pendingIntent = PendingIntent.getActivity(this, 1234, intent, 0)

        val notification =
            NotificationCompat.Builder(this, NotificationChannels.LOW_PRIORITY_CHANNEL_ID)
                .setContentTitle("SALE $title")
                .setContentText(description)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setSmallIcon(R.drawable.ic_message)
                .setLargeIcon(largeIcon)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build()
        NotificationManagerCompat.from(this).notify(12334, notification)

        Thread {
            loadBitMapImage(url) { image ->
                notification.largeIcon = image
                NotificationManagerCompat.from(this).notify(12334, notification)
            }
        }.start()
    }

    private fun loadBitMapImage(url: String?, callback: (image: Bitmap?) -> Unit) {
        if (!url.isNullOrEmpty()) {
            val bitmap = Glide.with(this)
                .asBitmap()
                .load(url)
                .submit()
                .get()
            callback(bitmap)
        }
    }

}