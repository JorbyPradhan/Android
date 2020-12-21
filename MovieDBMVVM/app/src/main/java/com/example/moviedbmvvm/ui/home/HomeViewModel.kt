package com.example.moviedbmvvm.ui.home

import androidx.lifecycle.ViewModel
import com.example.moviedbmvvm.data.repositories.MoviesRepository
import com.example.moviedbmvvm.util.lazyDeferred

class HomeViewModel(repo : MoviesRepository) : ViewModel() {

  //  val pageListLiveData1 : LiveData<PagedList<Movie>>
  /*  private val repository : MoviesRepository
    var pageListLiveData : LiveData<PagedList<Movie>> ?= null*/

    val pageListLiveData by lazyDeferred {
        repo.getMovies()
    }

    /*init {
        val movieDao = AppDatabase.invoke(application).getMovieDao()
        val networkConnectionInterceptor : NetworkConnectionInterceptor = NetworkConnectionInterceptor(application.applicationContext)
        val ma  = MovieApi.invoke(networkConnectionInterceptor)
        repository = MoviesRepository(movieDao,ma)
        Coroutines.io {
            repository.getMovies()
        }
        pageListLiveData = LivePagedListBuilder<Int,Movie>(
            repository.movielst,5
        ).build()
    }*/
}