package com.example.myanmarlotteryadmin.ui.home.alert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myanmarlotteryadmin.domain.AlertInterface

@Suppress("UNCHECKED_CAST")
class AlertViewModelFactory(
    private val alert : AlertInterface
) :ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AlertViewModel(alert) as T
    }
}