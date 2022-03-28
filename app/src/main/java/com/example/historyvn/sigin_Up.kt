package com.example.historyvn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class sigin_Up : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sigin_up)
    }

    fun SignIn_onClick (view: View){
        val SignIn = Intent(this, SignIn::class.java)
        startActivity(SignIn)
    }

    fun Login_onClick (view: View){
        val Login = Intent(this, News::class.java)
        startActivity(Login)
    }
}