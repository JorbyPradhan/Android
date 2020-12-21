package com.example.flowapi.network

import com.example.flowapi.model.Post
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPost():List<Post>
}