package com.example.myanmarlotteryadmin.ui.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myanmarlotteryadmin.domain.HomeInterface


@Suppress("UNCHECKED_CAST")
class OrderListFactory(private val home : HomeInterface): ViewModelProvider.NewInstanceFactory()
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return OrderListViewModel(home) as T
    }
}