package com.example.myanmarlottery.ui.lottery

import androidx.lifecycle.ViewModel
import com.example.myanmarlottery.data.repository.NumberRepo
import com.example.myanmarlottery.util.lazyDeferred

class ViewLotteryNumberViewModel(
    private val repo : NumberRepo
) : ViewModel() {
    val numbers by lazyDeferred {
        repo.getLotteryNumber()
    }
}