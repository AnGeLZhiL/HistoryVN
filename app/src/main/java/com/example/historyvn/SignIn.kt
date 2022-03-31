package com.example.historyvn

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
    }


    fun SignUp_onClick(view: View) {
        val SignUp = Intent(this, sigin_Up::class.java)
        startActivity(SignUp)
    }


    @DelicateCoroutinesApi
    fun Login_onClick(view: View) {
        val loginText = findViewById<EditText>(R.id.editTextLogin).text;
        val passwordText = findViewById<EditText>(R.id.editPassword).text;

        GlobalScope.launch(Dispatchers.IO) {
            auth(loginText.toString(), passwordText.toString());
        }
    }

    suspend fun auth(login: String, password: String) {
        val client = HttpClient()
        val request: HttpRequest =
            client.request("https://7f577c2e4794ec.lhrtunnel.link/api/auth") {
                method = HttpMethod.Post
                headers {
                    append(HttpHeaders.Accept, "application/json")
                }
                parameter("login", login)
                parameter("password", password)
            }
        println("------------------------------------------------------------------------------------------")
        println(request);
    }
}