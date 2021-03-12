package com.example.androidhomework.gitHub.ui.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidhomework.gitHub.data.RemoteRepository

class RepositoryDetailedViewModel: ViewModel() {

    private val repositoryListRepository = RepositoryDetailedRepository()

    private val repositoryLiveData = MutableLiveData<RemoteRepository>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()
    private val isErrorLiveData = MutableLiveData<Boolean>()
    private val isStaredLiveData = MutableLiveData<Boolean>()


    val repos: LiveData<RemoteRepository>
        get() = repositoryLiveData

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    val isError: LiveData<Boolean>
        get() = isErrorLiveData

    val isStared: LiveData<Boolean>
        get() = isStaredLiveData


    fun requestRepos(owner: String, name: String) {
        isLoadingLiveData.postValue(true)
        isErrorLiveData.postValue(false)
        isStaredLiveData.postValue(false)
        repositoryListRepository.getRepo(owner,name, { repo ->
            isLoadingLiveData.postValue(false)
            repositoryLiveData.postValue(repo)
        },{
            Log.d("module22", it.message)
            isErrorLiveData.postValue(true)
        })

        repositoryListRepository.checkIfStared(owner,name, {
            isStaredLiveData.postValue(it)
        },{
            Log.d("module22", it.message)
        })
    }

    fun starRepo(owner: String, name: String){
        repositoryListRepository.starRepo(owner,name,{
            isStaredLiveData.postValue(true)
        },{
            Log.d("module22", it.message)
        })
    }

    fun unstarRepo(owner: String, name: String){
        repositoryListRepository.unstarRepo(owner,name,{
            isStaredLiveData.postValue(false)
        },{
            Log.d("module22", it.message)
        })
    }



}