package com.naveen.networkcoroutines.repository

import com.naveen.networkcoroutines.network.ApiService
import com.naveen.networkcoroutines.network.Post

class NetworkRepository(private val apiService: ApiService) {
    suspend fun getDemoPost(): Result<Post> = runCatching {
        val response = apiService.getPost()
        if (response.isSuccessful && response.body() != null) {
            response.body()!!
        } else {
            throw IllegalStateException("GET failed: ${response.code()} ${response.message()}")
        }
    }

    suspend fun createDemoPost(title: String, body: String): Result<Post> = runCatching {
        val response = apiService.createPost(Post(title = title, body = body))
        if (response.isSuccessful && response.body() != null) {
            response.body()!!
        } else {
            throw IllegalStateException("POST failed: ${response.code()} ${response.message()}")
        }
    }
}


