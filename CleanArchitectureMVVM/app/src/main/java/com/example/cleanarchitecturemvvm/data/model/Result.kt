package com.example.cleanarchitecturemvvm.data.model


import com.google.gson.annotations.SerializedName

data class Result(
    val author: String,
    val content: String,
    val id: String,
    val url: String
)