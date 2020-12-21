package com.example.cleanarchitecturemvvm.domain

import androidx.lifecycle.MutableLiveData
import com.example.cleanarchitecturemvvm.data.model.Credit
import com.example.cleanarchitecturemvvm.data.model.Movie
import com.example.cleanarchitecturemvvm.data.model.MovieDetail
import com.example.cleanarchitecturemvvm.data.model.Reviews
import com.example.cleanarchitecturemvvm.data.repository.MovieRepository

class CallBackMovieImpl(private val repo : MovieRepository) : CallBackMovie {

    override suspend fun getTrendMovie(): MutableLiveData<List<Movie>> {
        return repo.getTrendMovie()
    }

    override suspend fun getPopularMovie(page : Int): MutableLiveData<List<Movie>> {
        return repo.getPopularMovie(page)
    }

    override suspend fun getNowPlayingMovie(page : Int): MutableLiveData<List<Movie>> {
        return repo.getNowPlayingMovie(page)
    }

    override suspend fun getUpcoming(page : Int): MutableLiveData<List<Movie>> {
        return repo.getUpComing(page)
    }

    override suspend fun getTopRated(page : Int): MutableLiveData<List<Movie>> {
        return repo.getTopRated(page)
    }

    override suspend fun getLatest(): MutableLiveData<Movie> {
        return repo.getLatest()
    }

    override suspend fun getDetail(id:String): MutableLiveData<MovieDetail> {
        return repo.getDetail(id.toInt())
    }

    override suspend fun getReview(id: String): MutableLiveData<Reviews> {
        return repo.getReview(id.toInt())
    }

    override suspend fun getCredit(id: String): MutableLiveData<Credit> {
        return repo.getCredit(id.toInt())
    }

    override suspend fun getPage(): Int {
        return repo.getPage()
    }
}