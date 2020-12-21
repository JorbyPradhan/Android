package com.example.daggerhiltforkotlin.model

import android.content.Context
import android.util.Log
import android.widget.Toast
import javax.inject.Inject

class Motorcycle @Inject constructor(private val engine: Engine, private val wheel: Wheel) {

    fun getCar(context: Context){
       /* engine.getEngine()
        wheel.getWheel()*/
        Log.i("motor", "My Car is amazing")
        Toast.makeText(context,"Car have ${engine.getEngine()} and ${ wheel.getWheel()}",Toast.LENGTH_SHORT).show()
    }
}