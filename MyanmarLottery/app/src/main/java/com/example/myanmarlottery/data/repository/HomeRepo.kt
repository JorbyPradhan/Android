package com.example.myanmarlottery.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myanmarlottery.data.model.LotteryChar
import java.util.ArrayList

class HomeRepo {
    private val listChar : ArrayList<LotteryChar> = ArrayList()
    fun getLotteryChar():LiveData<List<LotteryChar>>{
        fetchChar()
        val mutableListChar : MutableLiveData<List<LotteryChar>> = MutableLiveData()
        mutableListChar.postValue(listChar)
        return mutableListChar
    }

    private fun fetchChar(){
        if (listChar.size == 0){
            listChar.add(LotteryChar("က"))
            listChar.add(LotteryChar("ခ"))
            listChar.add(LotteryChar("ဂ"))
            listChar.add(LotteryChar("ဃ"))
            listChar.add(LotteryChar("င"))
            listChar.add(LotteryChar("စ"))
            listChar.add(LotteryChar("ဆ"))
            listChar.add(LotteryChar("ဇ"))
            listChar.add(LotteryChar("ဈ"))
            listChar.add(LotteryChar("ည"))
            listChar.add(LotteryChar("ဋ"))
            listChar.add(LotteryChar("ဌ"))
            listChar.add(LotteryChar("ဍ"))
            listChar.add(LotteryChar("ဎ"))
            listChar.add(LotteryChar("ဏ"))
            listChar.add(LotteryChar("တ"))
            listChar.add(LotteryChar("ထ"))
            listChar.add(LotteryChar("ဒ"))
            listChar.add(LotteryChar("ဓ"))
            listChar.add(LotteryChar("န"))
            listChar.add(LotteryChar("ပ"))
            listChar.add(LotteryChar("ဖ"))
            listChar.add(LotteryChar("ဗ"))
            listChar.add(LotteryChar("ဘ"))
            listChar.add(LotteryChar("မ"))
            listChar.add(LotteryChar("ယ"))
            listChar.add(LotteryChar("ရ"))
            listChar.add(LotteryChar("လ"))
            listChar.add(LotteryChar("ဝ"))
            listChar.add(LotteryChar("သ"))
            listChar.add(LotteryChar("ဟ"))
            listChar.add(LotteryChar("ဠ"))
            listChar.add(LotteryChar("အ"))
        }

    }
}