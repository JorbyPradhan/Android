package com.example.daggerhiltforkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.daggerhiltforkotlin.model.Main
import com.example.daggerhiltforkotlin.model.Motorcycle
import com.example.daggerhiltforkotlin.model.User
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var motorcycle : Motorcycle
    @Inject
    lateinit var main: Main

    @Inject
    lateinit var user : User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //motorcycle.getCar(this)
       // main.getName(this)
        user.getName(this)
    }
}