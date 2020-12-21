package com.example.cleanarchitecturemvvm.domain

import androidx.lifecycle.MutableLiveData
import com.example.cleanarchitecturemvvm.data.model.Credit
import com.example.cleanarchitecturemvvm.data.model.Reviews
import com.example.cleanarchitecturemvvm.data.model.TvShow
import com.example.cleanarchitecturemvvm.data.model.TvShowsDetail
import com.example.cleanarchitecturemvvm.data.repository.TvRepository

class CallBackTvShowImpl(private val repo : TvRepository) : CallBackTvShow {


    override suspend fun getPopularTv(page: Int): MutableLiveData<List<TvShow>> {
        return repo.getPopularTv(page)
    }

    override suspend fun getAiringToday(page: Int): MutableLiveData<List<TvShow>> {
        return repo.getAiringToday(page)
    }

    override suspend fun getOnTv(page: Int): MutableLiveData<List<TvShow>> {
       return repo.getOnTv(page)
    }

    override suspend fun getTopRatedTv(page: Int): MutableLiveData<List<TvShow>> {
       return repo.getTopRatedTv(page)
    }

    override suspend fun getLatestTv(): MutableLiveData<TvShow> {
       return repo.getLatestTv()
    }

    override suspend fun getShowDetail(id: String): MutableLiveData<TvShowsDetail> {
        return repo.getShowDetail(id.toInt())
    }

    override suspend fun getShowReview(id: String): MutableLiveData<Reviews> {
        return repo.getShowReview(id.toInt())
    }

    override suspend fun getShowCredit(id: String): MutableLiveData<Credit> {
        return repo.getShowCredit(id.toInt())
    }

    override suspend fun getTrendTv(): MutableLiveData<List<TvShow>> {
        return repo.getTrendTv()
    }
}