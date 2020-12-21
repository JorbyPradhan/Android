package com.example.myanmarlottery.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myanmarlottery.data.model.LottoModel
import java.util.ArrayList

class OrderRepo {
    fun getOrderNumber(): LiveData<List<LottoModel>> {
        val mutableList : MutableLiveData<List<LottoModel>> = MutableLiveData()
        val list = fetchNumber()
        mutableList.postValue(list)
        return mutableList
    }

    private fun fetchNumber(): ArrayList<LottoModel> {
        val listNumber : ArrayList<LottoModel> = ArrayList()
        if(listNumber.size == 0){
            listNumber.add(LottoModel("က",null,112211,10,10))
            listNumber.add(LottoModel("န",null,324611,3,3))
            listNumber.add(LottoModel("တ",null,654321,5,3))
            listNumber.add(LottoModel("အ",null,123345,5,5))
            listNumber.add(LottoModel("ထ",null,423341,10,10))
            listNumber.add(LottoModel("လ",null,342436,3,3))
        }
        return listNumber
    }
}