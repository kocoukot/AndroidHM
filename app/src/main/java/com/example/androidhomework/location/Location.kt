package com.example.androidhomework.location

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.Instant

sealed class Location: Parcelable {


    @Parcelize
    data class GotLocation(
        val id: Long,
        val lat: Double,
        val lng: Double,
        val alt: Double,
        val speed: Float,
        var createdAt: Instant
    ): Location()
}