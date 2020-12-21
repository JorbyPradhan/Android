package com.example.myanmarlottery.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myanmarlottery.data.repository.NumberRepo

@Suppress("UNCHECKED_CAST")
class DetailFactory (
    private val repo : NumberRepo
    ) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(repo) as T
    }
}