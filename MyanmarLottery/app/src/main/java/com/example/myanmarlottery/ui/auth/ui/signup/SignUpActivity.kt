package com.example.myanmarlottery.ui.auth.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myanmarlottery.R
import com.example.myanmarlottery.ui.auth.ui.verify.VerifyActivity
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        btn_signUp.setOnClickListener {
            intent = Intent(applicationContext,VerifyActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                //  it.putExtra("displayName", displayName)
                startActivity(it)
            }
        }
    }
}