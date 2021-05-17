package com.example.androidhomework.threading.ui

import android.util.Log
import com.example.androidhomework.roomdao.data.bd.Database
import com.example.androidhomework.threading.Movie
import com.example.androidhomework.threading.MovieType
import com.example.androidhomework.threading.Network
import com.example.androidhomework.threading.db.MovieDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException


class MovieRepository {

    private val movieDao = MovieDatabase.instance.movieDao()

    fun getMovieByID(
        movieTitle: String, movieType: String, callback: (List<Movie>) -> Unit,
        errorCallBack: (list:  Flow<List<Movie>>) -> Unit
    ) {

        Network.getMovieCall(
            movieTitle,
            movieType
        ).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("module21", "execute request error ${e.message}", e)

                errorCallBack(movieDao.activeMovies("%$movieTitle%", movieType))
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseString = response.body?.string().orEmpty()
                    val movies = parseMovieResponse(responseString)
                    movieDao.insertMovie(movies)
                    callback(movies)
                } else {
                    errorCallBack(movieDao.activeMovies("%$movieTitle%", movieType))
                }
            }
        })
    }


    private fun parseMovieResponse(responseBodyString: String): List<Movie> {
        return try {
            val jsonObj = JSONObject(responseBodyString)
            val movieArray = jsonObj.getJSONArray("Search")
            (0 until movieArray.length()).map { index -> movieArray.getJSONObject(index) }
                .map { movieJSONObject ->
                    val title = movieJSONObject.getString("Title")
                    val poster = movieJSONObject.getString("Poster")
                    val imdbID = movieJSONObject.getString("imdbID")
                    var type = movieJSONObject.getString("Type")

                    val movieType = when (type) {
                        "movie" -> MovieType.MOVIE
                        "series" -> MovieType.SERIES
                        else -> MovieType.EPISODE
                    }
                    Movie(
                        title,
                        imdbID,
                        poster,
                        movieType
                    )
                }

        } catch (e: JSONException) {
            emptyList()
        }
    }
}