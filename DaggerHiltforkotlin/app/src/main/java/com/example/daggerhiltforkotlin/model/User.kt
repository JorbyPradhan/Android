package com.example.daggerhiltforkotlin.model

import android.content.Context
import android.widget.Toast
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject
import javax.inject.Qualifier

class User @Inject constructor(
    @FName
    private val fName: String,
    @LName
    private val lName: String){
    fun getName(context: Context){
        Toast.makeText(context,"My name is $fName $lName", Toast.LENGTH_LONG).show()
    }
}

@Module
@InstallIn(ApplicationComponent::class)
class ModuleApp{

    @Provides
    @FName
    fun getFName() : String = "James"

    @Provides
    @LName
    fun getLName() : String = "Pradhan"

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FName

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LName

