package com.example.cleanarchitecturemvvm.domain

import androidx.lifecycle.MutableLiveData
import com.example.cleanarchitecturemvvm.data.model.Credit
import com.example.cleanarchitecturemvvm.data.model.Reviews
import com.example.cleanarchitecturemvvm.data.model.TvShow
import com.example.cleanarchitecturemvvm.data.model.TvShowsDetail

interface CallBackTvShow {
    suspend fun getPopularTv(page : Int): MutableLiveData<List<TvShow>>
    suspend fun getAiringToday(page : Int): MutableLiveData<List<TvShow>>
    suspend fun getOnTv(page : Int): MutableLiveData<List<TvShow>>
    suspend fun getTopRatedTv(page : Int): MutableLiveData<List<TvShow>>
    suspend fun getLatestTv(): MutableLiveData<TvShow>
    suspend fun getShowDetail(id : String): MutableLiveData<TvShowsDetail>
    suspend fun getShowReview(id:String):MutableLiveData<Reviews>
    suspend fun getShowCredit(id:String):MutableLiveData<Credit>
    suspend fun getTrendTv(): MutableLiveData<List<TvShow>>
}