package com.example.cleanarchitecturemvvm.domain

import androidx.lifecycle.MutableLiveData
import com.example.cleanarchitecturemvvm.data.model.DownloadLink
import com.example.cleanarchitecturemvvm.data.repository.ReadVideoRepo

class CallBackVideoImpl(
    private val repo : ReadVideoRepo
): CallBackVideo {
    override suspend fun getVideo(id: String): MutableLiveData<String> {
        return repo.getVideo(id.toInt())
    }

    override suspend fun getDownload(id: String): MutableLiveData<List<DownloadLink>> {
        return repo.getDownload(id.toInt())
    }

    override fun insertLink(trailer: String,link: String, link1: String, link2: String, id: String) {
        repo.insertLink(trailer,link, link1, link2, id)
    }
}