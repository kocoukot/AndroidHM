package com.example.androidhomework.files.data

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Url

interface FilesApi {
    @GET
    suspend fun getFile(
        @Url url: String
    ): ResponseBody
}