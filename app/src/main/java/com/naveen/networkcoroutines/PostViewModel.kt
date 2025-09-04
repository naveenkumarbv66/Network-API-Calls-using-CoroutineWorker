package com.naveen.networkcoroutines

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.naveen.networkcoroutines.workers.CreatePostWorker
import com.naveen.networkcoroutines.util.NetworkUtils

class PostViewModel(application: Application) : AndroidViewModel(application) {
    private val mutableText = MutableLiveData("")
    val text: LiveData<String> = mutableText

    private val mutableIsLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = mutableIsLoading

    private val mutableError = MutableLiveData<String?>(null)
    val error: LiveData<String?> = mutableError

    fun post() {
        if (!NetworkUtils.isInternetAvailable(getApplication())) {
            mutableError.value = "No internet connection"
            return
        }
        mutableIsLoading.value = true
        val input = Data.Builder()
            .putString("title", "Hello from WorkManager")
            .putString("body", "This is a demo post body")
            .build()
        val request = OneTimeWorkRequestBuilder<CreatePostWorker>()
            .setInputData(input)
            .build()
        val wm = WorkManager.getInstance(getApplication())
        wm.enqueue(request)
        wm.getWorkInfoByIdLiveData(request.id).observeForever { info: WorkInfo? ->
            if (info == null) return@observeForever
            if (info.state.isFinished) {
                val error = info.outputData.getString("error")
                mutableText.value = if (error != null) {
                    mutableError.value = error
                    "Error: $error"
                } else {
                    val id = info.outputData.getInt("id", -1)
                    val title = info.outputData.getString("title") ?: ""
                    val body = info.outputData.getString("body") ?: ""
                    "id=$id\n$title\n$body"
                }
                mutableIsLoading.value = false
            }
        }
    }

    fun clearError() {
        mutableError.value = null
    }
}


