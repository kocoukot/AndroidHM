package com.example.androidhomework.gitHub.ui.repository_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomework.gitHub.data.RemoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RepositoryListViewModel : ViewModel() {
    private val repositoryListRepository = RepositoryListRepository()


    private val repositoryListLiveData = MutableLiveData<List<RemoteRepository>>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()
    private val isErrorLiveData = MutableLiveData<Boolean>()

    private val viewModelCoroutineScope = CoroutineScope(Dispatchers.Main)

    val repos: LiveData<List<RemoteRepository>>
        get() = repositoryListLiveData

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    val isError: LiveData<Boolean>
        get() = isErrorLiveData


    fun requestRepos() {
        viewModelCoroutineScope.launch {
            try {
                isLoadingLiveData.postValue(true)
                isErrorLiveData.postValue(false)
                val repos = repositoryListRepository.getRepos()
                repositoryListLiveData.postValue(repos)
            } catch (t: Throwable) {
                isErrorLiveData.postValue(true)
                repositoryListLiveData.postValue(emptyList())
            } finally {
                isLoadingLiveData.postValue(false)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelCoroutineScope.cancel()
    }
}