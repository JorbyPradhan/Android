package com.example.cleanarchitecturemvvm.data.model.firestore

data class Video(
    val id : Int,
    val name : String,
    val trailer : String,
    val trailer1 : String,
    val trailer2 : String,
    val watchLink : String,
    val downloadLink : String,
    val downloadLink1: String,
    val downloadLink2: String
)