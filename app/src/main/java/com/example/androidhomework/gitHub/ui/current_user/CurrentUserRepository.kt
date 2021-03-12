package com.example.androidhomework.gitHub.ui.current_user

import android.util.Log
import com.example.androidhomework.gitHub.data.GitUser
import com.example.androidhomework.gitHub.net.NetworkGit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrentUserRepository {

    fun getCurrentUserInfo(
        callback: (GitUser?) -> Unit,
        errorCallBack: (e: Throwable) -> Unit
    ) {
        NetworkGit.githubApi.searchUsers().enqueue(object : Callback<GitUser> {
            override fun onFailure(call: Call<GitUser>, t: Throwable) {
                errorCallBack(t)
                Log.d("module22", t.message)
            }

            override fun onResponse(call: Call<GitUser>, response: Response<GitUser>) {
                if (response.isSuccessful) {
                    callback(response.body())
                } else {
                    Log.d("module22", "error response")
                }
            }

        })
    }
}
