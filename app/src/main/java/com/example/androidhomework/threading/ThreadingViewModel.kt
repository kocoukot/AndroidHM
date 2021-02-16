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
    private val isLoadingLiveData = MutableLiveData<Boolean>()
    private val isErrorLiveData = MutableLiveData<Boolean>()


    val movies: LiveData<List<Movie>>
        get() = moviesLiveData

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    val isError: LiveData<Boolean>
        get() = isErrorLiveData


    fun requestMovies(movieTitle: String, movieYear: String, movieType: String) {
        isLoadingLiveData.postValue(true)
        isErrorLiveData.postValue(false)
        moviesRepository.getMovieByID(movieTitle,movieYear,movieType, { movies ->
            isLoadingLiveData.postValue(false)
            moviesLiveData.postValue(movies)
        },{
            Log.d("module21", it.message)
            isErrorLiveData.postValue(true)
        })
    }
}