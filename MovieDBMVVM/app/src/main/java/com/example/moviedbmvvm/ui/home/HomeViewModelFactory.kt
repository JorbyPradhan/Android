package com.example.moviedbmvvm.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviedbmvvm.data.repositories.MoviesRepository

class HomeViewModelFactory(
    private val repo : MoviesRepository
) :ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  HomeViewModel(repo) as T
    }
}