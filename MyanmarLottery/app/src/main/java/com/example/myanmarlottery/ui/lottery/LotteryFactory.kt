package com.example.myanmarlottery.ui.lottery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myanmarlottery.data.repository.NumberRepo

@Suppress("UNCHECKED_CAST")
class LotteryFactory(private val repo: NumberRepo) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewLotteryNumberViewModel(repo) as T
    }
}