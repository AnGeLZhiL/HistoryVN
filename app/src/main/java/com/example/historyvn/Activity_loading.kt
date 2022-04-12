package com.example.historyvn

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Activity_loading : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
    }

    fun SignInOnClick (view: View){
        val SignIn = Intent(this, SignIn::class.java)
        startActivity(SignIn)
    }

    fun SignUp_onClick (view: View){
        val SignUp = Intent(this, SignUpActivity::class.java)
        startActivity(SignUp)
    }

    override fun onBackPressed() {
        // do nothing
    }
}