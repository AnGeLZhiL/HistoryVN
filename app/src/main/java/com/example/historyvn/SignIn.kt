package com.example.historyvn

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.historyvn.viewmodels.SignInViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class SignIn : AppCompatActivity() {

    private val viewModel: SignInViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        val textViewSignIn = findViewById<TextView>(R.id.textViewSignIn);
        textViewSignIn.setOnClickListener {
            Login_onClick()
        }

        lifecycleScope.launchWhenCreated {
            viewModel.loginState.collect {
                if (it) {
                    finishActivity(0)
                    startActivity(Intent(this@SignIn, News::class.java))
                }
            }
        }
    }

    fun SignUp_onClick(view: View) {
        val SignUp = Intent(this, SignUpActivity::class.java)
        finishActivity(0)
        startActivity(SignUp)
    }

    fun Login_onClick() {
        val loginText = findViewById<EditText>(R.id.editTextLogin).text;
        val passwordText = findViewById<EditText>(R.id.editPassword).text;

        lifecycleScope.launch {
            signIn(loginText.toString(), passwordText.toString())
        }
    }


    private suspend fun signIn(login: String, password: String) {
        viewModel.login(login, password)
            .onFailure { Toast.makeText(this, it.message, Toast.LENGTH_LONG).show() }
    }

}