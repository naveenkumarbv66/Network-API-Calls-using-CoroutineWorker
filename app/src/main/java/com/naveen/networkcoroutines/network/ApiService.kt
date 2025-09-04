package com.naveen.networkcoroutines.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

// Demo endpoints using jsonplaceholder.typicode.com
data class Post(val userId: Int? = null, val id: Int? = null, val title: String, val body: String)

interface ApiService {
    @GET("posts/1")
    suspend fun getPost(): Response<Post>

    @POST("posts")
    suspend fun createPost(@Body post: Post): Response<Post>
}


