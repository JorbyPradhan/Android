package com.example.cleanarchitecturemvvm.data.model


import com.google.gson.annotations.SerializedName

data class TvShowPage(
    val page: Int,
    val results: List<TvShow>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)