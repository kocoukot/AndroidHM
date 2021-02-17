package com.example.androidhomework.threading

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor


object Network {
    var networkFlipperPlugin = NetworkFlipperPlugin()


    private const val MOVIE_API_KEY = "3b89ac14"

    fun getMovieCall(movieTitle: String, movieYear: String, movieType: String): Call {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                .header("apikey", "3b89ac14")
            val request = requestBuilder.build()
            chain.proceed(request)
        }

        httpClient.addNetworkInterceptor( FlipperOkhttpInterceptor(networkFlipperPlugin))


        httpClient.addNetworkInterceptor(logging)

        val okHttpClient = httpClient.build()

        val url = HttpUrl.Builder()
            .scheme("http")
            .host("www.omdbapi.com")
            .addQueryParameter("apikey", MOVIE_API_KEY)
            .addQueryParameter("s", movieTitle)
            .addQueryParameter("y", movieYear)
            .addQueryParameter("type", movieType)

            .build()

        val request = Request.Builder()
            .get()
            .url(url)
            .build()
        return okHttpClient.newCall(request)
    }


}