package com.example.cleanarchitecturemvvm.data.model


import com.google.gson.annotations.SerializedName

data class Credit(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)