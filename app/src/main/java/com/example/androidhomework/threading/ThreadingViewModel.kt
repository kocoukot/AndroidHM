package com.example.androidhomework.threading

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidhomework.animals.SingleLiveEvent

class ThreadingViewModel : ViewModel() {

    private val moviesRepository = MovieRepository()

    private val moviesLiveData = MutableLiveData<List<Movie>>()
    private val showToastLiveData = SingleLiveEvent<Unit>()

    val movies: LiveData<List<Movie>>
        get() = moviesLiveData

    val showToast: LiveData<Unit>
        get() = showToastLiveData


    private val movieIds = listOf(
        "tt0133093",
        "tt0137523",
        "tt0109830",
        "tt0068646",
        "tt0167261"
    )

    fun requestMovies() {
        moviesRepository.getMovieByID(movieIds) { movies ->
            moviesLiveData.postValue(movies)
            showToastLiveData.postValue(Unit)
        }
    }


}