package com.example.cleanarchitecturemvvm.domain

import androidx.lifecycle.MutableLiveData
import com.example.cleanarchitecturemvvm.data.model.Credit
import com.example.cleanarchitecturemvvm.data.model.Movie
import com.example.cleanarchitecturemvvm.data.model.MovieDetail
import com.example.cleanarchitecturemvvm.data.model.Reviews

interface CallBackMovie {
   suspend fun getTrendMovie():MutableLiveData<List<Movie>>
   suspend fun getPopularMovie(page : Int):MutableLiveData<List<Movie>>
   suspend fun getNowPlayingMovie(page : Int):MutableLiveData<List<Movie>>
   suspend fun getUpcoming(page : Int):MutableLiveData<List<Movie>>
   suspend fun getTopRated(page : Int):MutableLiveData<List<Movie>>
   suspend fun getLatest():MutableLiveData<Movie>
   suspend fun getDetail(id:String):MutableLiveData<MovieDetail>
   suspend fun getReview(id:String):MutableLiveData<Reviews>
   suspend fun getCredit(id:String):MutableLiveData<Credit>
   suspend fun getPage():Int
}