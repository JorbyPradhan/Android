package com.example.cleanarchitecturemvvm.presentation.ui.detail

import androidx.lifecycle.ViewModel
import com.example.cleanarchitecturemvvm.domain.CallBackMovie
import com.example.cleanarchitecturemvvm.domain.CallBackTvShow
import com.example.cleanarchitecturemvvm.domain.CallBackVideo
import com.example.cleanarchitecturemvvm.util.lazyDeferred

class DetailViewModel(
    private val movieD : CallBackMovie,
    private val tvD : CallBackTvShow,
    private val videoD : CallBackVideo
) : ViewModel() {
    var id : String ?= null
    val movieDetail by lazyDeferred {
        movieD.getDetail(id!!)
    }
    val showDetail by lazyDeferred {
        tvD.getShowDetail(id!!)
    }
    val movieReviews by lazyDeferred {
        movieD.getReview(id!!)
    }
    val showReviews by lazyDeferred {
        tvD.getShowReview(id!!)
    }
    val movieCredit by lazyDeferred {
        movieD.getCredit(id!!)
    }
    val showCredit by lazyDeferred {
        tvD.getShowCredit(id!!)
    }
    val videoLink by lazyDeferred {
        videoD.getVideo(id!!)
    }
    val downloadLink by lazyDeferred {
        videoD.getDownload(id!!)
    }

    fun addLink(trailer: String,link : String, link1: String , link2: String){
        videoD.insertLink(trailer,link,link1,link2,id!!)
    }
}