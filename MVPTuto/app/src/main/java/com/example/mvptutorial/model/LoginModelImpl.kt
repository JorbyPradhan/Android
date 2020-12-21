package com.example.mvptutorial.model

import android.text.TextUtils
import com.example.mvptutorial.`interface`.LoginModel
import com.example.mvptutorial.`interface`.LoginPresenter

class LoginModelImpl(private val presenter:LoginPresenter) : LoginModel {

    override fun validateUser(username: String, pass: String) {
        if(TextUtils.isEmpty(username)){
            presenter.onError("Please Enter Username")
            return
        }
        if(username != "abc@gmail.com"){
            presenter.onError("Please Enter Valid Username")
            return
        }
        if (pass.isEmpty()){
            presenter.onError("Please Enter Password")
            return
        }
        if(pass != "123"){
            presenter.onError("Please Valid Password")
            return
        }
        else{
           presenter.onSuccess()
        }

    }
}