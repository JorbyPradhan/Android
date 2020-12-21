package com.example.myanmarlotteryadmin.ui.home.addpost

import androidx.lifecycle.ViewModel
import com.example.myanmarlotteryadmin.domain.HomeInterface
import com.example.myanmarlotteryadmin.domain.HomeInterfaceImpl
import com.example.myanmarlotteryadmin.util.lazyDeferred

class AddViewModel(private val home : HomeInterface) : ViewModel() {
   val lotteryList by lazyDeferred {
       home.getLotteryNumber()
   }
}