package com.example.androidhomework.gitHub.ui.repo

import android.util.Log
import com.example.androidhomework.gitHub.net.NetworkGit
import com.example.androidhomework.gitHub.data.RemoteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryDetailedRepository {

    fun getRepo(
        owner: String,
        name: String,
        callback: (RemoteRepository) -> Unit,
        errorCallBack: (e: Throwable) -> Unit
    ) {
        NetworkGit.githubApi.searchRepoDetailed(owner, name)
            .enqueue(object : Callback<RemoteRepository> {
                override fun onFailure(call: Call<RemoteRepository>, t: Throwable) {
                    errorCallBack(t)
                    Log.d("module22", "error message ${t.message}")
                }

                override fun onResponse(
                    call: Call<RemoteRepository>,
                    response: Response<RemoteRepository>
                ) {
                    if (response.isSuccessful) {
                        response?.body()?.let { callback(it) }
                    } else {
                        Log.d("module22", "error response on detailed repo")
                    }
                }

            })


    }

    fun checkIfStared(
        owner: String,
        name: String,
        callback: (Boolean) -> Unit,
        errorCallBack: (e: Throwable) -> Unit
    ) {
        NetworkGit.githubApi.checkIfStared(owner, name).enqueue(object : Callback<Boolean> {
            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                errorCallBack(t)
                Log.d("module22", "error message ${t.message}")
            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.isSuccessful) {
                    callback(true)
                } else {
                    callback(false)
                    Log.d("module22", "error response on check if stared or not stared")
                }
            }

        })
    }

    fun starRepo(
        owner: String,
        name: String,
        callback: (Boolean) -> Unit,
        errorCallBack: (e: Throwable) -> Unit
    ) {
        NetworkGit.githubApi.starRepo(owner, name).enqueue(object : Callback<Boolean> {
            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                errorCallBack(t)
                Log.d("module22", "error message ${t.message}")
            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.isSuccessful) {
                    callback(true)
                } else {
                    Log.d("module22", "error response on check if stared or not stared")
                }
            }
        })
    }

    fun unstarRepo(
        owner: String,
        name: String,
        callback: (Boolean) -> Unit,
        errorCallBack: (e: Throwable) -> Unit
    ) {
        NetworkGit.githubApi.unstarRepo(owner, name).enqueue(object : Callback<Boolean> {
            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                errorCallBack(t)
                Log.d("module22", "error message ${t.message}")
            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.isSuccessful) {
                    callback(true)
                } else {
                    Log.d("module22", "error response on check if stared or not stared")
                }
            }
        })
    }

}