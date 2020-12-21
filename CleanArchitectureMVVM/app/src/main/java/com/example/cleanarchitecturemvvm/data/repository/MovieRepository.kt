package com.example.cleanarchitecturemvvm.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cleanarchitecturemvvm.data.model.Credit
import com.example.cleanarchitecturemvvm.data.model.Movie
import com.example.cleanarchitecturemvvm.data.model.MovieDetail
import com.example.cleanarchitecturemvvm.data.model.Reviews
import com.example.cleanarchitecturemvvm.data.remote.MovieApi
import com.example.cleanarchitecturemvvm.data.remote.SafeApiRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(
    private val api: MovieApi
    ) :SafeApiRequest(){  //private val prefs: PreferenceProvider

    private val key = "7142a40c54b2c690a1f53e697a1d51aa"
    suspend fun getTrendMovie(): MutableLiveData<List<Movie>> {
        return withContext(Dispatchers.IO){
            fetchTrendMovie()
        }
    }
    suspend fun getPopularMovie(page: Int): MutableLiveData<List<Movie>> {
        return withContext(Dispatchers.IO){
            fetchPopularMovie(page)
        }
    }

    suspend fun getNowPlayingMovie(page: Int): MutableLiveData<List<Movie>> {
        return withContext(Dispatchers.IO){
            fetchNowPlaying(page)
        }
    }
    suspend fun getUpComing(page :Int): MutableLiveData<List<Movie>> {
        return withContext(Dispatchers.IO){
            fetchUpComing(page)
        }
    }
    suspend fun getTopRated(page: Int): MutableLiveData<List<Movie>> {
        return withContext(Dispatchers.IO){
            fetchTopRated(page)
        }
    }
    suspend fun getLatest(): MutableLiveData<Movie> {
        return withContext(Dispatchers.IO){
            fetchLatest()
        }
    }
    suspend fun getPage(): Int {
        return withContext(Dispatchers.IO){
            fetchPage()
        }
    }
    suspend fun getDetail(id: Int):MutableLiveData<MovieDetail>{
        return withContext(Dispatchers.IO){
          fetchDetail(id)
        }
    }
    suspend fun getReview(id: Int):MutableLiveData<Reviews>{
        return withContext(Dispatchers.IO){
          fetchReviews(id)
        }
    }

    private suspend fun fetchReviews(id: Int): MutableLiveData<Reviews> {
        val reviews = MutableLiveData<Reviews>()
        val response = apiRequest { api.getReviews(id,key) }
        reviews.postValue(response)
        return reviews
    }
    suspend fun getCredit(id: Int):MutableLiveData<Credit>{
        return withContext(Dispatchers.IO){
            fetchCredit(id)
        }
    }

    private suspend fun fetchCredit(id: Int): MutableLiveData<Credit> {
        val credit = MutableLiveData<Credit>()
        val response = apiRequest { api.getCredit(id,key) }
        credit.postValue(response)
        return credit
    }

    private suspend fun fetchDetail(id: Int):MutableLiveData<MovieDetail>{
        val movieDetail = MutableLiveData<MovieDetail>()
        val response = apiRequest { api.getMovieDetail(id,key) }
        movieDetail.postValue(response)
        return movieDetail
    }

    private suspend fun fetchLatest(): MutableLiveData<Movie> {
        val movies = MutableLiveData<Movie>()
        val response = apiRequest{api.getLatest(key)}
        movies.postValue(response)
        Log.i("latestM","Lasted${response.posterPath}")
        return movies
    }
    private suspend fun fetchNowPlaying(page : Int): MutableLiveData<List<Movie>> {
        val movies = MutableLiveData<List<Movie>>()
        val response = apiRequest{api.getNowPlayingMovie(key,page)}
        movies.postValue(response.results)
        return movies
    }
    private suspend fun fetchUpComing(page : Int): MutableLiveData<List<Movie>> {
        val movies = MutableLiveData<List<Movie>>()
        val response = apiRequest{api.getUpcoming(key,page)}
        movies.postValue(response.results)
        return movies
    }
    private suspend fun fetchTopRated(page: Int): MutableLiveData<List<Movie>> {
        val movies = MutableLiveData<List<Movie>>()
        val response = apiRequest{api.getTopRated(key,page)}
        movies.postValue(response.results)
        return movies
    }

    private suspend fun fetchPopularMovie(page : Int) :MutableLiveData<List<Movie>>{
        //val lastSavedAt = prefs.getLastSavedAt()
        //if(lastSavedAt == null){
        val movies = MutableLiveData<List<Movie>>()
        val response = apiRequest{api.getPopularMovie(key,page)}
        //response.results[0].page = response.page+1
        Log.i("MyPage", "Page $page Hi ${response.results}")
        movies.postValue(response.results)
        return movies
        //}
    }

    private suspend fun fetchTrendMovie() :MutableLiveData<List<Movie>>{
        //val lastSavedAt = prefs.getLastSavedAt()
        //if(lastSavedAt == null){
         val movies = MutableLiveData<List<Movie>>()
            val response = apiRequest{api.getTrendMovie(key)}
            //response.results[0].page = response.page+1
            //Log.i("MyPage", "Page ${response.results[0].page} Hi ${response.results}")
            movies.postValue(response.results)
            return movies
        //}
    }
    private suspend fun fetchPage():Int{
        val response = apiRequest {api.getPopularMovie(key,1)}
        return response.page
    }
}