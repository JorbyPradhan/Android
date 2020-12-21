package com.example.daggerhiltforkotlin.model

import android.util.Log
import javax.inject.Inject

class Engine {

    @Inject
    constructor()

    fun getEngine() : String{
        Log.i("Engine","Engine started.........")
        return "Engine"
    }
}