package com.example.cleanarchitecturemvvm.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitecturemvvm.domain.CallBackMovie
import com.example.cleanarchitecturemvvm.domain.CallBackTvShow

class HomeViewModelFactory(private val domain :CallBackMovie, private val domain1: CallBackTvShow)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  HomeViewModel(domain,domain1) as T
    }
}