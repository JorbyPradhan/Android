package com.example.myanmarlotteryadmin.ui.order

import androidx.lifecycle.ViewModel
import com.example.myanmarlotteryadmin.domain.HomeInterface
import com.example.myanmarlotteryadmin.util.lazyDeferred

class OrderListViewModel(private val home : HomeInterface) : ViewModel() {
    val lotteryList by lazyDeferred {
        home.getPostList()
    }
}