package com.example.flowapi.repo

import com.example.flowapi.model.Post
import com.example.flowapi.network.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PostRepository {
    companion object {
        fun getPost(): Flow<List<Post>> = flow {
            val response = RetrofitBuilder.api.getPost()
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}