package com.naveen.networkcoroutines.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.naveen.networkcoroutines.network.RetrofitClient
import com.naveen.networkcoroutines.repository.NetworkRepository

class GetPostWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        val repo = NetworkRepository(RetrofitClient.apiService)
        val result = repo.getDemoPost()
        return result.fold(
            onSuccess = { post ->
                val output = Data.Builder()
                    .putString("title", post.title)
                    .putString("body", post.body)
                    .putInt("id", post.id ?: -1)
                    .build()
                Result.success(output)
            },
            onFailure = { error ->
                Result.failure(Data.Builder().putString("error", error.message).build())
            }
        )
    }
}


