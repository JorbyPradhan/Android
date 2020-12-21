package com.example.myanmarlottery.ui.detail

import androidx.lifecycle.ViewModel
import com.example.myanmarlottery.data.repository.NumberRepo
import com.example.myanmarlottery.util.lazyDeferred

class DetailViewModel(
    repo : NumberRepo
) : ViewModel() {
    var num : String ?= null
    var ticket : String ?= null
    val numbers by lazyDeferred {
        repo.getLottoDetail(ticket!!,num!!)
    }
}