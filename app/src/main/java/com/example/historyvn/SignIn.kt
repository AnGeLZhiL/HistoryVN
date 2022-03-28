package com.example.historyvn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
    }


    fun SignUp_onClick (view: View){
        val SignUp = Intent(this, sigin_Up::class.java)
        startActivity(SignUp)
    }

    fun Login_onClick (view: View){
        val Login = Intent(this, News::class.java)
        startActivity(Login)
    }
}