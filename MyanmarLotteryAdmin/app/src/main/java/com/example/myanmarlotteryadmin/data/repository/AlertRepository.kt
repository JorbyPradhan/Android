package com.example.myanmarlotteryadmin.data.repository

import androidx.lifecycle.MutableLiveData
import java.util.ArrayList

class AlertRepository {
    private val msg : ArrayList<String> = ArrayList()
    fun getAlert():MutableLiveData<List<String>>{
        fetchAlert()
        val mutableList: MutableLiveData<List<String>> = MutableLiveData()
        mutableList.postValue(msg)
        return mutableList
    }

    private fun fetchAlert() {
        msg.add("Jobby want က ၅၃၂၃၂၁")
        msg.add("Jobby want ခ ၄၅၄၆၅၁")
        msg.add("Sai Thu want ဆ ၂၃၄၃၅၃")
        msg.add("Ko Kyaw want ရ ၄၃၆၂၂၃")
        msg.add("Aung Naing Oo want အစ ၅၃")
        msg.add("KyawThuWin want အဆုံး ၂၁")
    }
}