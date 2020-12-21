package com.example.cleanarchitecturemvvm.data.model


import com.google.gson.annotations.SerializedName

data class ProductionCompanyX(
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: Any,
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String
)