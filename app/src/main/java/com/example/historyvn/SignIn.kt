package com.example.historyvn

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.historyvn.viewmodels.SignInViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


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
                    Toast.makeText(applicationContext, "Удачная авторизация", Toast.LENGTH_LONG).show()
                    finishActivity(0)
                    startActivity(Intent(this@SignIn, News::class.java))
                }
            }
        }
    }

    private fun bytesToHexString(bytes: ByteArray): String? {
        val sb = StringBuffer()
        for (i in bytes.indices) {
            val hex = Integer.toHexString(0xFF and bytes[i].toInt())
            if (hex.length == 1) {
                sb.append('0')
            }
            sb.append(hex)
        }
        return sb.toString()
    }

    fun SignUp_onClick(view: View) {
        val SignUp = Intent(this, SignUpActivity::class.java)
        finishActivity(0)
        startActivity(SignUp)
    }

    fun Login_onClick() {
        val loginText = findViewById<EditText>(R.id.editTextLogin).text;
        val passwordText = findViewById<EditText>(R.id.editPassword).text;

        var digest: MessageDigest? = null
        var hash: String = ""
        try {
            digest = MessageDigest.getInstance("SHA-256")
            digest.update(passwordText.toString().toByteArray())
            hash = bytesToHexString(digest.digest()).toString()
            Log.i("Eamorr", "result is $hash")
        } catch (e1: NoSuchAlgorithmException) {
            // TODO Auto-generated catch block
            e1.printStackTrace()
        }

        lifecycleScope.launch {
            signIn(loginText.toString(), hash)
        }
    }


    private suspend fun signIn(login: String, password: String) {
        viewModel.login(login, password)
            .onFailure { Toast.makeText(this, "Пользователь не найден", Toast.LENGTH_LONG).show() }
    }

}