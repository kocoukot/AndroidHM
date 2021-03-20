package com.example.androidhomework.files

import android.util.Log
import com.example.androidhomework.files.data.FilesNetwork
import okhttp3.ResponseBody


class FilesRepository {

    suspend fun getFile(url: String): ResponseBody {
        return FilesNetwork.api.getFile(url)
    }
}