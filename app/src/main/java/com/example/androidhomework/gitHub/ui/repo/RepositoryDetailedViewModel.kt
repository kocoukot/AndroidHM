package com.example.androidhomework.gitHub.ui.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomework.gitHub.data.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RepositoryDetailedViewModel : ViewModel() {

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
        viewModelScope.launch {
            try {
                isLoadingLiveData.postValue(true)
                isErrorLiveData.postValue(false)
                isStaredLiveData.postValue(false)
                val repo = repositoryListRepository.getRepo(owner, name)
                repositoryLiveData.postValue(repo)
            } catch (t: Throwable) {
                isErrorLiveData.postValue(true)
                Log.d("module23", t.message)
            } finally {
                isLoadingLiveData.postValue(false)
            }
        }

        viewModelScope.launch {
            try {
                val isStared = repositoryListRepository.checkIfStared(owner, name)
                isStaredLiveData.postValue(isStared)
            } catch (t: Throwable) {
                Log.d("module23", t.message)
            }
        }
    }

    fun starRepo(owner: String, name: String) {

        viewModelScope.launch {
            try {

                repositoryListRepository.starRepo(owner, name, {
                    isStaredLiveData.postValue(true)
                }, {
                    Log.d("module23", it.message)
                })
            } catch (t: Throwable) {
                Log.d("module23", "VM error ${t.message}")
            }

        }
    }

    fun unstarRepo(owner: String, name: String) {
        viewModelScope.launch {
            try {
                repositoryListRepository.unstarRepo(owner, name, {
                    isStaredLiveData.postValue(false)
                }, {
                    Log.d("module23", it.message)
                })
            } catch (t: Throwable) {
                Log.d("module23", t.message)
            }
        }
    }

//        repositoryListRepository.unstarRepo(owner, name, {
//            isStaredLiveData.postValue(false)
//        }, {
//            Log.d("module22", it.message)
//        })
//    }


}