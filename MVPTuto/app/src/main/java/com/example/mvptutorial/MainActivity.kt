package com.example.mvptutorial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mvptutorial.`interface`.LoginPresenter
import com.example.mvptutorial.`interface`.LoginView
import com.example.mvptutorial.presenter.LoginPresenterImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),LoginView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val presenter : LoginPresenter = LoginPresenterImpl(this)
        card_login.setOnClickListener {
            presenter.onHandlesLogin(edt_name.text.toString().trim(),edt_password.text.toString().trim())
        }
    }

    override fun onSuccess() {
         intent = Intent(applicationContext, MainActivity2::class.java).also {
            startActivity(it)
             finish()
        }
    }

    override fun onFailure() {
        TODO("Not yet implemented")
    }

    override fun onError(msg: String) {
       Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }
}