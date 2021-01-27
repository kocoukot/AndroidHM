package com.example.androidhomework.threading

import android.util.Log
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*


class MovieRepository {

    fun getMovieByID(movieID: List<String>, callback: (List<Movie>) -> Unit) {
        val allMovieList = Collections.synchronizedList(mutableListOf<Movie>())

        val threads = movieID.chunked(1).map { movieChunk ->
            Thread {
                Log.d("test test", Thread.currentThread().name)

                val movies = movieChunk.mapNotNull { movieID ->
                    getMovie(movieID)
                }

                allMovieList.addAll(movies)
            }
        }
        threads.forEach { it.start() }
        threads.forEach { it.join() }
        callback(allMovieList)
    }

    private fun getMovie(movieId: String): Movie? {
        return try {
            val response: Response = Network.getMovieCall(movieId).execute()
            val json = response.body()?.string().orEmpty()
            parseMovieResponse(json)
        } catch (e: IOException) {
            null
        }

    }

    private fun parseMovieResponse(responseBodyString: String): Movie? {
        return try {
            val jsonObj = JSONObject(responseBodyString)
            val title = jsonObj.getString("Title")
            val year = jsonObj.getString("Year")
            val poster = jsonObj.getString("Poster")
            val imdbID = jsonObj.getString("imdbID")
            Movie(title, imdbID, year, poster)
        } catch (e: JSONException) {
            null
        }
    }

}