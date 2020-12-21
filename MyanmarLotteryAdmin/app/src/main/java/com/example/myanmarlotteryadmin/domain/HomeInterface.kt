package com.example.myanmarlotteryadmin.domain

import androidx.lifecycle.MutableLiveData
import com.example.myanmarlotteryadmin.data.model.Post

interface HomeInterface {
    fun getLotteryNumber(): MutableLiveData<List<Post>>
    fun getPostList(): MutableLiveData<List<Post>>
}