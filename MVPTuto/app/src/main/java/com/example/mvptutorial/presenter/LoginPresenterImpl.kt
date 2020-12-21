package com.example.mvptutorial.presenter

import com.example.mvptutorial.`interface`.LoginModel
import com.example.mvptutorial.`interface`.LoginPresenter
import com.example.mvptutorial.`interface`.LoginView
import com.example.mvptutorial.model.LoginModelImpl

class LoginPresenterImpl(private val view:LoginView) : LoginPresenter {

    override fun onHandlesLogin(email: String, password: String) {
       val model:LoginModel = LoginModelImpl(this)
        model.validateUser(email,password)
    }

    override fun onSuccess() {
        view.onSuccess()
    }

    override fun onFailure() {
       view.onFailure()
    }

    override fun onError(msg: String) {
        view.onError(msg)
    }
}