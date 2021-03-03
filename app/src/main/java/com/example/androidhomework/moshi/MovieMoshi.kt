package com.example.androidhomework.moshi

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class MovieMoshi(
    @Json(name = "Title")
    val title: String,

    @Json(name = "Year")
    val year: String,

    @Json(name = "Rated")
    val rated: RatedMoshi = RatedMoshi.GENERAL,

    @Json(name = "Genre")
    val genre: String,

    @Json(name = "Poster")
    val poster: String,

    @Json(name = "Ratings")
    var ratings: Map<String, String> = emptyMap()
)
