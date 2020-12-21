package com.example.myanmarlotteryadmin.ui.home.addpost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myanmarlotteryadmin.domain.HomeInterface

@Suppress("UNCHECKED_CAST")
class AddViewModelFactory(private val home : HomeInterface):ViewModelProvider.NewInstanceFactory()
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddViewModel(home) as T
    }
}