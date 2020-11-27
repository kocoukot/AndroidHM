package com.example.androidhomework

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class Animals: Parcelable {

    @Parcelize
    data class Common(
        val id: Long,
        val name: String,
        val imageLink: String,
        val familyType: String
    ): Animals()

    @Parcelize
    data class Rare(
        val id: Long,
        val name: String,
        val imageLink: String,
        val familyType: String,
        val rarity: String
    ): Animals()
}