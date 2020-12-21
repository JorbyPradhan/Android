package com.example.mvptutorial.`interface`

interface LoginPresenter {

    fun onHandlesLogin(email:String,password:String)
    fun onSuccess()
    fun onFailure()
    fun onError(msg:String)
}