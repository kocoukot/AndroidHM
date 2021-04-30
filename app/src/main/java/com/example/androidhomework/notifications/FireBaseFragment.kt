package com.example.androidhomework.notifications

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.androidhomework.R
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.fragment_firebase.*
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FireBaseFragment : Fragment(R.layout.fragment_firebase) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val notificationManager =
            requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancelAll()

        getToken.setOnClickListener {
            getToken()
        }

        synchronize.setOnClickListener {
            val action = FireBaseFragmentDirections.actionFireBaseFragmentToSynchronizeFragment()
            findNavController().navigate(action)
        }
    }

    private fun getToken() {
        lifecycleScope.launch {
            val token = getTokenSuspend()
            Log.d("Module29", token.toString())
        }
    }

    private suspend fun getTokenSuspend(): String? = suspendCoroutine { continuation ->
        FirebaseMessaging.getInstance().token
            .addOnSuccessListener { token ->
                Log.d("Module29", "success")
                continuation.resume(token)
            }

            .addOnFailureListener { exception ->
                Log.d("Module29", "exception")
                continuation.resume(null)
            }

            .addOnCanceledListener {
                continuation.resume(null)
            }
    }
}