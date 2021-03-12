package com.example.androidhomework.gitHub.ui.repository_list

import android.util.Log
import com.example.androidhomework.gitHub.net.NetworkGit
import com.example.androidhomework.gitHub.data.RemoteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryListRepository {

    fun getRepos(
        callback: (List<RemoteRepository>) -> Unit,
        errorCallBack: (e: Throwable) -> Unit
    ) {

        NetworkGit.githubApi.searchRepos().enqueue(object : Callback<List<RemoteRepository>> {
            override fun onFailure(call: Call<List<RemoteRepository>>, t: Throwable) {
                errorCallBack(t)
                Log.d("module22", "error message ${t.message}")
            }

            override fun onResponse(call: Call<List<RemoteRepository>>, response: Response<List<RemoteRepository>>) {
                if (response.isSuccessful) {
                    callback(response.body().orEmpty())
                } else {
                    Log.d("module22", "error response")
                }
            }

        })
    }


}

