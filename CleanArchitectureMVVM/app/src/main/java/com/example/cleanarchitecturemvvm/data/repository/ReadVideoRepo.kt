package com.example.cleanarchitecturemvvm.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cleanarchitecturemvvm.data.model.DownloadLink
import com.example.cleanarchitecturemvvm.data.model.firestore.Video
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class ReadVideoRepo {

    private var trailer: String? = null
    val data: MutableLiveData<String> = MutableLiveData()
    val download: MutableLiveData<List<DownloadLink>> = MutableLiveData()
    val downloadList: ArrayList<DownloadLink> = ArrayList()
    private val db = FirebaseFirestore.getInstance()

    suspend fun getVideo(id: Int): MutableLiveData<String> {
        return withContext(Dispatchers.IO) {
            fetchVideo(id)
            delay(2000)
            data
        }
    }

    suspend fun getDownload(id: Int): MutableLiveData<List<DownloadLink>> {
        return withContext(Dispatchers.IO) {
            fetchDownload(id)
            delay(2000)
            download
        }
    }

    private fun fetchVideo(id: Int) {
        Log.i("OnSuccess", "Success  $id")
        db.document("Movie/$id").get().addOnSuccessListener {
            if (it.exists()) {
                trailer = it.getString("trailerLink")
                data.postValue(trailer)
            }
            Log.i("OnSuccess", "Success $trailer $id")
        }.addOnFailureListener {
            Log.e("OnFailure", "Fail ${it.message}")
        }
    }

    private fun fetchDownload(id: Int) {
        db.document("Movie/$id").get().addOnSuccessListener {
            if (it.exists()) {
                downloadList.add(DownloadLink(it.getString("DownloadLink")))
                downloadList.add(DownloadLink(it.getString("DownloadLink1")))
                downloadList.add(DownloadLink(it.getString("DownloadLink2")))
            }
            download.postValue(downloadList)

            Log.i("OnSuccess", "Success $trailer $id")
        }.addOnFailureListener {
            Log.e("OnFailure", "Fail ${it.message}")
        }
    }
    fun insertLink(trailer : String, link : String, link1: String, link2: String, id:String){
        val downLink= HashMap<String,Any>()
        downLink["trailerLink"] = trailer
        downLink["DownloadLink"] = link
        downLink["DownloadLink1"] = link1
        downLink["DownloadLink2"] = link2
        db.collection("Movie").document(id).set(downLink)
    }
}