package com.example.androidhomework.threading.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidhomework.threading.Movie
import com.example.androidhomework.threading.MovieType
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class ThreadingViewModel : ViewModel() {

    private val viewModelCoroutineScope = CoroutineScope(Dispatchers.IO)

    private val moviesRepository =
        MovieRepository()

    private val moviesLiveData = MutableLiveData<List<Movie>>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()
    private val isErrorLiveData = MutableLiveData<Boolean>()


    val movies: LiveData<List<Movie>>
        get() = moviesLiveData

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    val isError: LiveData<Boolean>
        get() = isErrorLiveData


//    fun requestMovies(movieTitle: String, movieType: String) {
//        isLoadingLiveData.postValue(true)
//        isErrorLiveData.postValue(false)
//        moviesRepository.getMovieByID(movieTitle, movieType, { movies ->
//            isLoadingLiveData.postValue(false)
//            moviesLiveData.postValue(movies)
//        }, {
//            Log.d("module21", it.message!!)
//            isErrorLiveData.postValue(true)
//        })
//    }

    suspend fun getMovieFlow(movieNameFlow: Flow<String>, movieTypeFlow: Flow<MovieType>) {
        combine(
            movieNameFlow,
            movieTypeFlow
        ) { name, type ->
            name to type.name

        }.debounce(500)
            .mapLatest { movie ->
                moviesRepository.getMovieByID(movie.first, movie.second, { movies ->
                    isLoadingLiveData.postValue(false)
                    moviesLiveData.postValue(movies)
                }, { list ->
                    list.map { list ->
                        if (list.isNullOrEmpty()) {
                            isErrorLiveData.postValue(true)
                        } else{
                            isErrorLiveData.postValue(false)
                            isLoadingLiveData.postValue(false)
                            moviesLiveData.postValue(list)
                        }
                    }.launchIn(viewModelCoroutineScope)
                })
            }
            .launchIn(viewModelCoroutineScope)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelCoroutineScope.cancel()
    }

}