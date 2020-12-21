package com.example.myanmarlotteryadmin.ui.home.alert

import androidx.lifecycle.ViewModel
import com.example.myanmarlotteryadmin.domain.AlertInterface
import com.example.myanmarlotteryadmin.util.lazyDeferred

class AlertViewModel(private val alert : AlertInterface) : ViewModel() {
  val list by lazyDeferred {
      alert.getAlert()
  }
}