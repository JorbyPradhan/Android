package com.example.myanmarlottery.ui.alert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myanmarlottery.data.repository.OrderRepo

@Suppress("UNCHECKED_CAST")
class ShowUserViewModelFactory(
    private val repo : OrderRepo
) :ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShowUserAlertViewModel(repo) as T
    }
}