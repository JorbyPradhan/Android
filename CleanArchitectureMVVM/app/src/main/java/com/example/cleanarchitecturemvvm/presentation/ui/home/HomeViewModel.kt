package com.example.cleanarchitecturemvvm.presentation.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecturemvvm.data.model.Movie
import com.example.cleanarchitecturemvvm.data.model.TvShow
import com.example.cleanarchitecturemvvm.domain.CallBackMovie
import com.example.cleanarchitecturemvvm.domain.CallBackTvShow
import com.example.cleanarchitecturemvvm.util.lazyDeferred
import kotlinx.coroutines.launch

class HomeViewModel(
    private val movieD : CallBackMovie,
    private val tvD : CallBackTvShow) : ViewModel() {


    private var movieList : MutableLiveData<List<Movie>> ?= null
    private var tvShowList : MutableLiveData<List<TvShow>> ?= null
    var page : Int = 1

    val mutableLiveTrendData by lazyDeferred {
        movieD.getTrendMovie()
    }
    val mutableLiveTrendTvData by lazyDeferred {
        tvD.getTrendTv()
    }
    val mutableLiveLatest by lazyDeferred {
        movieD.getLatest()
    }
    val mutableLiveDataLatestTv by lazyDeferred {
        tvD.getLatestTv()
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    /* suspend fun getMovie():LiveData<List<Movie>>{
        if (movieList == null){
            movieList = movieD.getPopularMovie()
        }
        return movieList as LiveData<List<Movie>>
    }*/
    val mutableLiveMovieData by lazyDeferred {
        if (movieList == null)
            movieList = movieD.getPopularMovie(page)
        movieList
    }

    val mutableLiveTvData by lazyDeferred {
        if(tvShowList == null)
            tvShowList = tvD.getPopularTv(page)
        tvShowList
    }

    suspend fun changeTvShowType(title : String) {
        when (title) {
            "Top Rated" -> {
                Log.i("Topper", title)
                tvShowList?.postValue(tvD.getTopRatedTv(page).value)
                //movieList.postValue(movieD.getTrendMovie())
            }
            "Airing Today" -> {
                tvShowList?.postValue(tvD.getAiringToday(page).value)
            }
            "On TV" -> {
                tvShowList?.postValue(tvD.getOnTv(page).value)
            }
            else -> {
                tvShowList?.postValue(tvD.getPopularTv(page).value)
            }
        }
    }
     suspend fun changeType(title : String){
         when (title) {
            "Top Rated" -> {
                Log.i("Topper",title)
                movieList?.postValue(movieD.getTopRated(page).value)
                //movieList.postValue(movieD.getTrendMovie())
            }
            "Now Playing" -> {
                movieList?.postValue(movieD.getNowPlayingMovie(page).value)
            }
            "Upcoming" -> {
                movieList?.postValue(movieD.getUpcoming(page).value)
            }
            else -> {
                movieList?.postValue(movieD.getPopularMovie(page).value)
            }
        }
     }

    fun nextPage(type:CharSequence,title:String){
        page += 1
        if(type == "TVShows"){
            Log.i("Typer","${type.toString()}${page}")
            viewModelScope.launch {
                changeTvShowType(title)
            }
        }
       else {
            viewModelScope.launch {
                changeType(title)
            }
        }
    }
    fun previousPage(type:CharSequence,title: String){
        page -= 1
        if(type == "TVShows"){
            viewModelScope.launch {
                changeTvShowType(title)
            }
        }else {
            viewModelScope.launch {
                changeType(title)
            }
        }
    }
}