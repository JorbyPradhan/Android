package com.example.cleanarchitecturemvvm.domain

import androidx.lifecycle.MutableLiveData
import com.example.cleanarchitecturemvvm.data.model.DownloadLink
import com.example.cleanarchitecturemvvm.data.model.TvShow

interface CallBackVideo {
    suspend fun getVideo(id : String): MutableLiveData<String>
    suspend fun getDownload(id : String): MutableLiveData<List<DownloadLink>>
    fun insertLink(trailer: String, link:String,link1: String,link2: String,id: String)
}