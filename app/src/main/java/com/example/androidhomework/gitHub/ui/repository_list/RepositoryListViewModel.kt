package com.example.androidhomework.gitHub.ui.repository_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidhomework.gitHub.data.RemoteRepository

class RepositoryListViewModel: ViewModel() {
    private val repositoryListRepository = RepositoryListRepository()

    private val repositoryListLiveData = MutableLiveData<List<RemoteRepository>>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()
    private val isErrorLiveData = MutableLiveData<Boolean>()


    val repos: LiveData<List<RemoteRepository>>
        get() = repositoryListLiveData

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    val isError: LiveData<Boolean>
        get() = isErrorLiveData


    fun requestRepos() {
        isLoadingLiveData.postValue(true)
        isErrorLiveData.postValue(false)
        repositoryListRepository.getRepos({ repos ->
            isLoadingLiveData.postValue(false)
            repositoryListLiveData.postValue(repos)
        },{
            Log.d("module22", it.message)
            isErrorLiveData.postValue(true)
        })
    }
}