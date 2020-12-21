package com.example.myanmarlotteryadmin.domain

import androidx.lifecycle.MutableLiveData
import com.example.myanmarlotteryadmin.data.model.Post
import com.example.myanmarlotteryadmin.data.repository.HomeRepository

class HomeInterfaceImpl(private val repo : HomeRepository):HomeInterface {
    override fun getLotteryNumber(): MutableLiveData<List<Post>> {
       return repo.getLotteryList()
    }

    override fun getPostList(): MutableLiveData<List<Post>> {
        return repo.getPostList()
    }
}