package com.example.myanmarlotteryadmin.domain

import androidx.lifecycle.MutableLiveData

interface AlertInterface {
    fun getAlert():MutableLiveData<List<String>>
}