package com.example.androidhomework.notifications

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.androidhomework.R
import kotlinx.android.synthetic.main.fragment_synchronize.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SynchronizeFragment : Fragment(R.layout.fragment_synchronize) {

    private val receiver = NetworkBroadcastReceiver()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        synchronizeButton.setOnClickListener {
            if (receiver.hasNet) {
                synchronizing()
            } else {
                Toast.makeText(context, "Connect to the internet", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun synchronizing() {
        val notificationBuilder = NotificationCompat.Builder(
            requireContext(),
            NotificationChannels.HIGH_PRIORITY_CHANNEL_ID
        )
            .setContentTitle("Synchronizing")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSmallIcon(R.drawable.ic_message)

        synchronizeButton.isEnabled = false

        val maxProgress = 10
        lifecycleScope.launch {
            (0 until maxProgress).forEach { progress ->
                val notification = notificationBuilder
                    .setProgress(maxProgress, progress, false)
                    .build()

                NotificationManagerCompat.from(requireContext()).notify(5555, notification)

                delay(250)
            }
            val notificationComplete = notificationBuilder
                .setContentTitle("Finished")
                .setProgress(0, 0, false)
                .setAutoCancel(true)
                .build()

            NotificationManagerCompat.from(requireContext()).notify(5555, notificationComplete)
            synchronizeButton.isEnabled = true

        }
    }

    override fun onResume() {
        super.onResume()
        requireContext().registerReceiver(
            receiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    override fun onPause() {
        super.onPause()
        requireContext().unregisterReceiver(receiver)
    }
}