package com.example.historyvn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Activity_loading : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
    }

    fun SignIn_onClick (view: View){
        val SignIn = Intent(this, SignIn::class.java)
        startActivity(SignIn)
    }

    fun SignUp_onClick (view: View){
        val SignUp = Intent(this, sigin_Up::class.java)
        startActivity(SignUp)
    }
}