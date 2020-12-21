package com.example.myanmarlotteryadmin.domain

import androidx.lifecycle.MutableLiveData
import com.example.myanmarlotteryadmin.data.repository.AlertRepository

class AlertImpl(private val repo : AlertRepository) : AlertInterface {
    override fun getAlert(): MutableLiveData<List<String>> {
        return repo.getAlert()
    }
}