package com.example.androidhomework.threading

import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request


object Network {

   private val client = OkHttpClient()
    private const val MOVIE_API_KEY = "3b89ac14"

    fun getMovieCall(movieId: String): Call{
        val url = HttpUrl.Builder()
            .scheme("http")
            .host("www.omdbapi.com")
            .addQueryParameter("apikey", MOVIE_API_KEY)
            .addQueryParameter("i", movieId)
            .build()

        val request = Request.Builder()
            .get()
            .url(url)
            .build()
        return client.newCall(request)
    }


}