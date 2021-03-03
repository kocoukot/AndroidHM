package com.example.androidhomework.moshi

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Ratings (

    @Json(name = "Source")
    val source: String,

    @Json(name = "Value")
    val value: String
)