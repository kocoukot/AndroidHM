package com.example.androidhomework.moshi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ThreadingViewModelMoshi : ViewModel() {

    private val moviesRepository = MovieRepositoryMoshi()

    private val moviesLiveData = MutableLiveData<MovieMoshi>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()
    private val isErrorLiveData = MutableLiveData<Boolean>()
    private val isMovieFound = MutableLiveData<Boolean>()


    val movies: LiveData<MovieMoshi>
        get() = moviesLiveData

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    val isError: LiveData<Boolean>
        get() = isErrorLiveData

    val isFound: LiveData<Boolean>
        get() = isMovieFound

    fun requestMovies(movieTitle: String) {
        isLoadingLiveData.postValue(true)
        isErrorLiveData.postValue(false)
        moviesRepository.getMovieByID(movieTitle, { movies ->
            isLoadingLiveData.postValue(false)
            moviesLiveData.postValue(movies)
            isMovieFound.postValue(true)
        }, {
            Log.d("module21", it.message!!)
            isErrorLiveData.postValue(true)
        }, {
            isLoadingLiveData.postValue(false)
            isMovieFound.postValue(false)
        })
    }

    fun addRating() {

        Log.d("module22", moviesLiveData.value!!.ratings.toString())
        moviesRepository.addRating(movies.value!!) { movie ->
            moviesLiveData.postValue(movie)
        }

    }
}