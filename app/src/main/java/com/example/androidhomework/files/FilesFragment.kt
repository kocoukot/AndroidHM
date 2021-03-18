package com.example.androidhomework.files

import android.app.DownloadManager
import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.androidhomework.R
import com.example.androidhomework.files.data.FilesNetwork
import kotlinx.android.synthetic.main.fragment_files.*
import kotlinx.coroutines.*
import java.io.File
import java.net.URL

class FilesFragment : Fragment(R.layout.fragment_files) {

    private val testURL1 = "https://raw.githubusercontent.com/kean/Nuke/master/README.md"
    private val testURL2 = "https://github.com/Juanpe/SkeletonView/raw/main/README.md"

    private val repo = FilesRepository()

    private val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e("module24", "error from CoroutineExceptionHandler", throwable)
    }

    private val fileScope = CoroutineScope(Dispatchers.IO + errorHandler)

    val sharedPref by lazy {
        requireContext().getSharedPreferences(
            SHARED_PREF,
            Context.MODE_PRIVATE
        )
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        urlEditText.setText(testURL2)
        if (!sharedPref.getBoolean(IS_FIRST_LOAD_KEY, false)) {
            sharedPref.edit().putBoolean(IS_FIRST_LOAD_KEY, true).apply()
        }
        buttonDownload.setOnClickListener {
            isLoading()

            val urlText = urlEditText.text.toString()
            var fileName = urlText.substring(urlText.lastIndexOf("/") + 1)
            val intStor = requireContext().filesDir
            val start = System.currentTimeMillis()
            fileName = "${start}_$fileName"
            val file = File(intStor, fileName)

            if (sharedPref.getString(urlText, "") == "") {
                fileScope.launch {

                    /* if (Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED) {
                      Log.d("module24", "no SD card")
                      return@launch
                  }*/

                    try {
                        file.outputStream().buffered().use { fileOutputStream ->
                            repo.getFile(urlText)
                                .byteStream()
                                .use { inputStream ->
                                    inputStream.copyTo(fileOutputStream)
                                }
                        }

                        withContext(Dispatchers.Main) {
                            showToast("File $fileName downloaded")
                        }
                        sharedPref.edit()
                            .putString(urlText, fileName)
                            .apply()

                    } catch (t: Throwable) {
                        file.delete()
                        Log.d("module24", "error ${t.message}")
                    } finally {
                        withContext(Dispatchers.Main) {
                            isLoading()
                        }
                    }
                }
            } else {
                isLoading()
                showToast("Already downloaded")
            }
        }
    }

    private fun showToast(toastText: String) {
        Toast.makeText(requireContext(), toastText, Toast.LENGTH_SHORT).show()
    }

    private fun isLoading() {
        loadingProgress.isVisible = !loadingProgress.isVisible
        buttonDownload.isEnabled = !buttonDownload.isEnabled
        urlEditText.isEnabled = !urlEditText.isEnabled
    }

    companion object {
        const val SHARED_PREF = "shared_pref_KEY"
        const val IS_FIRST_LOAD_KEY = "first_load_key"
    }
}