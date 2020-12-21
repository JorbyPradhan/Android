package com.example.myanmarlotteryadmin.ui.home.viewpost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myanmarlotteryadmin.domain.HomeInterface

@Suppress("UNCHECKED_CAST")
class DisplayViewModelFactory (private val home : HomeInterface): ViewModelProvider.NewInstanceFactory()
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DisplayViewModel(home) as T
    }
}