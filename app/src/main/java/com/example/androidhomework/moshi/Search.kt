package com.example.androidhomework.moshi

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Search (
    val Search:List<MovieMoshi>,
    val Response: String
)