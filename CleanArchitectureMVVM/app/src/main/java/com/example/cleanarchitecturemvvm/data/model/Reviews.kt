package com.example.cleanarchitecturemvvm.data.model


import com.google.gson.annotations.SerializedName

data class Reviews(
    val id: Int,
    val page: Int,
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)