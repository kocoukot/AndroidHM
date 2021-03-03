package com.example.androidhomework.moshi

import com.squareup.moshi.Json

enum class RatedMoshi {
    GENERAL,
    PG,
    @Json(name = "PG-13")
    PG_13,
    R,
    @Json(name = "TV-PG")
    TV_PG,
    NC_17
}