package com.example.myanmarlotteryadmin.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myanmarlotteryadmin.data.model.Post

class HomeRepository {
    private val listData: ArrayList<Post> = ArrayList()
    private val postList: ArrayList<Post> = ArrayList()

    fun getPostList(): MutableLiveData<List<Post>> {
        fetchPost()
        val mutableData: MutableLiveData<List<Post>> = MutableLiveData()
        mutableData.postValue(postList)
        return mutableData
    }

    private fun fetchPost() {
        postList.add(Post("က", 123232, 10))
        postList.add(Post("ခ", 323123, 5))
        postList.add(Post("ဆ", 232532, 3))
        postList.add(Post("တ", 123566, 10))
        postList.add(Post("အ", 653223, 3))
        postList.add(Post("ပ", 678897, 5))
    }

    fun getLotteryList(): MutableLiveData<List<Post>> {
        fetchLottery()
        val mutableListChar: MutableLiveData<List<Post>> = MutableLiveData()
        mutableListChar.postValue(listData)
        return mutableListChar
    }

    private fun fetchLottery() {
        listData.add(Post("က", 111111, null))
        listData.add(Post("က", 222222, null))
        listData.add(Post("က", 131581, null))
        listData.add(Post("က", 123566, null))
        listData.add(Post("က", 343221, null))
        listData.add(Post("က", 678896, null))
    }
}