package com.example.myanmarlottery.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myanmarlottery.data.repository.HomeRepo

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(
    private val repo: HomeRepo
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(repo) as T
    }
}