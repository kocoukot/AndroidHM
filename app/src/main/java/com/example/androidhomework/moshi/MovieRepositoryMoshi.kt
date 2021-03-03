package com.example.androidhomework.moshi

import android.util.Log
import com.squareup.moshi.Moshi
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import java.lang.Exception


class MovieRepositoryMoshi {

    fun getMovieByID(
        movieTitle: String, callback: (MovieMoshi?) -> Unit,
        errorCallBack: (e: Throwable) -> Unit,
        noFilmCallback: () -> Unit
    ) {

        NetworkMoshi.getMovieCall(movieTitle).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("module21", "execute request error ${e.message}", e)
                errorCallBack(e)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseString = response.body?.string().orEmpty()
                    val movies = convertJsonToMovie(responseString)
                    if (movies != null) {
                        callback(movies)
                    } else {
                        noFilmCallback()
                    }
                } else {
                    errorCallBack(IOException("unsuccess response 111"))

                }
            }
        })
    }

    private fun convertJsonToMovie(responseBodyString: String): MovieMoshi? {
        val moshi = Moshi.Builder()
            .add(MovieCustomAdapter())
            .build()
        val adapter = moshi.adapter(MovieMoshi::class.java).nonNull()
        return try {
            val movies = adapter.fromJson(responseBodyString)
            movies
        } catch (e: Exception) {
            Log.d("module22", "error message ${e.message}")
            null
        }

    }

    fun addRating(movie: MovieMoshi, callback: (MovieMoshi?) -> Unit) {
        val rating = mapOf("Personal rate" to "${(0..10).random()} / 10")

        movie.ratings = movie.ratings + rating
        callback(movie)
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(MovieMoshi::class.java).nonNull()

        try {
            val movieToJson = adapter.toJson(movie)
            Log.d("module22", "movie to JSON  $movieToJson")
        } catch (e: Exception) {
            Log.d("module22", "error message ${e.message}")

        }



    }
}