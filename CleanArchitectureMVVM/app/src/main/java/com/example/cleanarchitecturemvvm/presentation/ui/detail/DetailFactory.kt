package com.example.cleanarchitecturemvvm.presentation.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitecturemvvm.domain.CallBackMovie
import com.example.cleanarchitecturemvvm.domain.CallBackTvShow
import com.example.cleanarchitecturemvvm.domain.CallBackVideo
import com.example.cleanarchitecturemvvm.presentation.ui.home.HomeViewModel

class DetailFactory(
    private val domain: CallBackMovie,
    private val domain1: CallBackTvShow,
    private val domain2: CallBackVideo
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(domain, domain1,domain2) as T
    }
}