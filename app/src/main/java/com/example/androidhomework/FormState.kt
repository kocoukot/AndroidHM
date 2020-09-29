package com.example.androidhomework

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_main.*

@Parcelize
data class FormState(
    var valid: Boolean,
    var message: String
): Parcelable

