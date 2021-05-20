package com.example.androidhomework.files

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.androidhomework.files.data.FilesNetwork
import kotlinx.coroutines.delay
import java.io.File

class DownloadWorker(
    private val context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val urlToDownload = inputData.getString(DOWNLOAD_URL_KEY) ?: ""
        return downloadFile(urlToDownload)
    }

    private suspend fun downloadFile(url: String): Result {

        var fileName = url.substring(url.lastIndexOf("/") + 1)
        val intStor = context.filesDir
        val start = System.currentTimeMillis()
        fileName = "${start}_$fileName"
        val file = File(intStor, fileName)
        return try {
            file.outputStream().buffered().use { fileOutputStream ->
                FilesNetwork.api.getFile(url)
                    .byteStream()
                    .use { inputStream ->
                        inputStream.copyTo(fileOutputStream)
                    }
            }
            Result.success()
        } catch (t: Throwable) {
            file.delete()
            Result.retry()
        }
    }

    companion object {
        const val DOWNLOAD_URL_KEY = "download ulr"
    }


}

