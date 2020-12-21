package com.example.myanmarlotteryadmin.ui.home.viewpost

import androidx.lifecycle.ViewModel
import com.example.myanmarlotteryadmin.domain.HomeInterface
import com.example.myanmarlotteryadmin.util.lazyDeferred

class DisplayViewModel(private val home : HomeInterface) : ViewModel() {
    val lotteryList by lazyDeferred {
        home.getPostList()
    }
}