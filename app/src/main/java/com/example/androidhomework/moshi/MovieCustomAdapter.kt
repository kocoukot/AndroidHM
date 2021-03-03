package com.example.androidhomework.moshi

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

class MovieCustomAdapter {

    @FromJson
    fun fromJsom(customMovie: CustomMovie):MovieMoshi{
        val map = customMovie.ratings.map { it.source to it.value }.toMap()
        return MovieMoshi(
            title = customMovie.title,
            year = customMovie.year,
            rated = customMovie.rated,
            genre = customMovie.genre,
            poster = customMovie.poster,
            ratings = map
        )
    }


    @JsonClass(generateAdapter = true)
    data class CustomMovie(
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
        var ratings: List<Ratings>
    )

    @JsonClass(generateAdapter = true)
    data class Ratings (

        @Json(name = "Source")
        val source: String,

        @Json(name = "Value")
        val value: String
    )




}