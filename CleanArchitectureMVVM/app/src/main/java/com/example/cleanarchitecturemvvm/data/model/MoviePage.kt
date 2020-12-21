package com.example.cleanarchitecturemvvm.data.model


import com.google.gson.annotations.SerializedName

data class MoviePage(
    val page: Int,
    val results: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)