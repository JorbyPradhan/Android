package com.example.daggerhiltforkotlin.model

import android.content.Context
import android.util.Log
import android.widget.Toast
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject
import javax.inject.Singleton

interface One {
    fun getName()
}

class ImplementOne @Inject constructor(private val name: String): One{

    override fun getName() {
        Log.i("main", "My name is $name ")
    }
}

class Main @Inject constructor(private val one: One){
    fun getName(context: Context){
        one.getName()
        Toast.makeText(context,"My name is James",Toast.LENGTH_LONG).show()
    }
}

/*@Module
@InstallIn(ApplicationComponent::class)
abstract class AppModule{

    @Binds
    @Singleton
    abstract fun binding(
        implementOne: ImplementOne
    ): One

}*/

//another way by @Provides

@Module
@InstallIn(ApplicationComponent::class)
class AppModule{

    @Provides
    @Singleton
    fun getName() = "James"

    @Provides
    @Singleton
    fun binding(name : String) :One = ImplementOne(name)

}
