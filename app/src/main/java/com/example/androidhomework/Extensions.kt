package com.example.androidhomework

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

fun <T: Fragment> T.withArguments(action: Bundle.() ->Unit): T{
    return apply{
        val args = Bundle().apply(action)
        arguments = args
    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun hasQ(): Boolean{
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
}
