package com.example.myanmarlottery.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myanmarlottery.data.model.LottoModel
import java.util.ArrayList

class NumberRepo {


    fun getLotteryNumber(): LiveData<List<LottoModel>> {
        val mutableList : MutableLiveData<List<LottoModel>> = MutableLiveData()
        val list = fetchNumber()
        mutableList.postValue(list)
        return mutableList
    }

    fun getLottoDetail(ticket: String, number:String): LiveData<List<LottoModel>>{
        val mutableData : MutableLiveData<List<LottoModel>> = MutableLiveData()
        val list = fetchDetail(ticket.toInt(),number.toInt())
        mutableData.postValue(list)
        return mutableData
    }

    private fun fetchDetail(ticket: Int, number: Int) : ArrayList<LottoModel>{
        val listDetail : ArrayList<LottoModel> = ArrayList()
        for(i in 0 until ticket){
            listDetail.add(LottoModel(null, null, number+i,null,10))
        }
        return listDetail
    }

    private fun fetchNumber(): ArrayList<LottoModel>{
        val listNumber : ArrayList<LottoModel> = ArrayList()
        if(listNumber.size == 0){
            listNumber.add(LottoModel(null,null,112211,20,10))
            listNumber.add(LottoModel(null,null,324611,13,3))
            listNumber.add(LottoModel(null,null,654321,25,5))
            listNumber.add(LottoModel(null,null,123345,50,5))
            listNumber.add(LottoModel(null,null,423341,50,10))
            listNumber.add(LottoModel(null,null,342436,39,3))
        }
        return listNumber
    }
}