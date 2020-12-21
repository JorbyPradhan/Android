package com.example.cleanarchitecturemvvm.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cleanarchitecturemvvm.data.model.*
import com.example.cleanarchitecturemvvm.data.remote.MovieApi
import com.example.cleanarchitecturemvvm.data.remote.SafeApiRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TvRepository(
    private val api: MovieApi
) : SafeApiRequest() {  //private val prefs: PreferenceProvider

    private val key = "7142a40c54b2c690a1f53e697a1d51aa"

    suspend fun getTrendTv(): MutableLiveData<List<TvShow>> {
        return withContext(Dispatchers.IO) {
            fetchTrendTv()
        }
    }

    suspend fun getPopularTv(page: Int): MutableLiveData<List<TvShow>> {
        return withContext(Dispatchers.IO) {
            fetchPopularTv(page)
        }
    }

    suspend fun getAiringToday(page: Int): MutableLiveData<List<TvShow>> {
        return withContext(Dispatchers.IO) {
            fetchAiringToday(page)
        }
    }

    suspend fun getOnTv(page: Int): MutableLiveData<List<TvShow>> {
        return withContext(Dispatchers.IO) {
            fetchOnTv(page)
        }
    }

    suspend fun getTopRatedTv(page: Int): MutableLiveData<List<TvShow>> {
        return withContext(Dispatchers.IO) {
            fetchTopRatedTv(page)
        }
    }

    suspend fun getLatestTv(): MutableLiveData<TvShow> {
        return withContext(Dispatchers.IO) {
            fetchLatest()
        }
    }

    suspend fun getPage(): Int {
        return withContext(Dispatchers.IO) {
            fetchPage()
        }
    }
    suspend fun getShowDetail(id: Int):MutableLiveData<TvShowsDetail>{
        return withContext(Dispatchers.IO){
            fetchShowDetail(id)
        }
    }
    suspend fun getShowReview(id: Int):MutableLiveData<Reviews>{
        return withContext(Dispatchers.IO){
            fetchShowReviews(id)
        }
    }
    suspend fun getShowCredit(id: Int):MutableLiveData<Credit>{
        return withContext(Dispatchers.IO){
            fetchShowCredit(id)
        }
    }

    private suspend fun fetchShowCredit(id: Int): MutableLiveData<Credit> {
        val credit = MutableLiveData<Credit>()
        val response = apiRequest { api.getShowCredit(id,key) }
        credit.postValue(response)
        return credit
    }
    private suspend fun fetchShowReviews(id: Int): MutableLiveData<Reviews> {
        val reviews = MutableLiveData<Reviews>()
        val response = apiRequest { api.getShowReviews(id,key) }
        reviews.postValue(response)
        return reviews
    }
    private suspend fun fetchShowDetail(id: Int):MutableLiveData<TvShowsDetail>{
        val showDetail = MutableLiveData<TvShowsDetail>()
        val response = apiRequest { api.getTvDetail(id,key) }
        showDetail.postValue(response)
        return showDetail
    }
    private suspend fun fetchLatest(): MutableLiveData<TvShow> {
        val tvShow = MutableLiveData<TvShow>()
        val response = apiRequest { api.getLatestTv(key) }
        tvShow.postValue(response)
        Log.i("latest","Lasted${response.posterPath}")
        return tvShow
    }

    private suspend fun fetchAiringToday(page: Int): MutableLiveData<List<TvShow>> {
        val tvShow = MutableLiveData<List<TvShow>>()
        val response = apiRequest { api.getAiringToday(key, page) }
        tvShow.postValue(response.results)
        return tvShow
    }

    private suspend fun fetchOnTv(page: Int): MutableLiveData<List<TvShow>> {
        val tvShow = MutableLiveData<List<TvShow>>()
        val response = apiRequest { api.getOnTv(key, page) }
        tvShow.postValue(response.results)
        return tvShow
    }

    private suspend fun fetchTopRatedTv(page: Int): MutableLiveData<List<TvShow>> {
        val tvShow = MutableLiveData<List<TvShow>>()
        val response = apiRequest { api.getTopRatedTv(key, page) }
        tvShow.postValue(response.results)
        return tvShow
    }

    private suspend fun fetchPopularTv(page: Int): MutableLiveData<List<TvShow>> {
        //val lastSavedAt = prefs.getLastSavedAt()
        //if(lastSavedAt == null){
        val tvShow = MutableLiveData<List<TvShow>>()
        val response = apiRequest { api.getPopularTv(key, page) }
        //response.results[0].page = response.page+1
        Log.i("MyPage", "Page $page Hi ${response.results}")
        tvShow.postValue(response.results)
        return tvShow
        //}
    }

    private suspend fun fetchTrendTv(): MutableLiveData<List<TvShow>> {
        //val lastSavedAt = prefs.getLastSavedAt()
        //if(lastSavedAt == null){
        val tvShow = MutableLiveData<List<TvShow>>()
        val response = apiRequest { api.getTrendTv(key) }
        //response.results[0].page = response.page+1
        //Log.i("MyPage", "Page ${response.results[0].page} Hi ${response.results}")
        tvShow.postValue(response.results)
        return tvShow
        //}
    }

    private suspend fun fetchPage(): Int {
        val response = apiRequest { api.getPopularMovie(key, 1) }
        return response.page
    }
}
