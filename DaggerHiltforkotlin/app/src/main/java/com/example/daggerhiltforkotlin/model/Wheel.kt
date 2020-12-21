package com.example.daggerhiltforkotlin.model

import android.util.Log
import javax.inject.Inject

class Wheel {

    @Inject
    constructor()

    fun getWheel() : String{
        Log.i("Wheel", "Wheel is started ....")
        return "Wheel"
    }
}