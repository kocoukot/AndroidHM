package com.example.androidhomework.files.data

import com.example.androidhomework.gitHub.net.GitHubApi
import com.example.androidhomework.gitHub.net.SuccessAccessToken
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

object FilesNetwork {

    private val okhttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://google.com")
        .client(okhttpClient)
        .build()

    val api: FilesApi
        get() = retrofit.create()
}