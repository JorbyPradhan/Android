package com.example.myanmarlottery.ui.alert

import androidx.lifecycle.ViewModel
import com.example.myanmarlottery.data.repository.NumberRepo
import com.example.myanmarlottery.data.repository.OrderRepo
import com.example.myanmarlottery.util.lazyDeferred

class ShowUserAlertViewModel ( private val repo : OrderRepo
) : ViewModel() {
    val numbers by lazyDeferred {
        repo.getOrderNumber()
    }
}