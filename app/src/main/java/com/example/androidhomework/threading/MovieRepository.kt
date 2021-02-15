package com.example.androidhomework.threading

import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*


class MovieRepository {

    fun getMovieByID(movieTitle: String, movieYear: String, movieType: String, callback: (List<Movie>) -> Unit,
                     errorCallBack: (e: Throwable) -> Unit) {

        Network.getMovieCall(movieTitle, movieYear, movieType).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
              Log.d("module21", "execute request error ${e.message}",e)
                errorCallBack(e)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful){
                    val responseString = response.body?.string().orEmpty()
                    val movies = parseMovieResponse(responseString)
                    callback(movies)
                } else {
                    errorCallBack(IOException("unsuccess response 111"))
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
                    val year = movieJSONObject.getString("Year")
                    val poster = movieJSONObject.getString("Poster")
                    val imdbID = movieJSONObject.getString("imdbID")
                    val type = movieJSONObject.getString("Type")
                    Movie(title, imdbID, year, poster, type)
                }
        } catch (e: JSONException) {
            emptyList()
        }
    }

}