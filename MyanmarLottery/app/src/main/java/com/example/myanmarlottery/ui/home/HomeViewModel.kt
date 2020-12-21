package com.example.myanmarlottery.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myanmarlottery.data.repository.HomeRepo
import com.example.myanmarlottery.util.lazyDeferred

class HomeViewModel(
     repo : HomeRepo
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    val mutableCharList by lazyDeferred {
        repo.getLotteryChar()
    }
}