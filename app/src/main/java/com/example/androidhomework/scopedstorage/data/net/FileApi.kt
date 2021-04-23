package com.example.androidhomework.scopedstorage.data.net

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Url

interface FileApi {
    @GET
    suspend fun getFile(
        @Url url: String
    ): ResponseBody
}