package com.example.cleanarchitecturemvvm.data.model


import com.google.gson.annotations.SerializedName

data class CreatedBy(
    @SerializedName("credit_id")
    val creditId: String,
    val gender: Int,
    val id: Int,
    val name: String,
    @SerializedName("profile_path")
    val profilePath: Any
)