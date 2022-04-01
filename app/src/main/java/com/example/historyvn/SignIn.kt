package com.example.historyvn

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.util.logging.Logger


class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        val textViewSignIn = findViewById<TextView>(R.id.textViewSignIn);
        textViewSignIn.setOnClickListener {
            Login_onClick()
        }
    }


    fun SignUp_onClick() {
        val SignUp = Intent(this, sigin_Up::class.java)
        startActivity(SignUp)
    }


    /*@DelicateCoroutinesApi
    fun Login_onClick(view: View) {
        val loginText = findViewById<EditText>(R.id.editTextLogin).text;
        val passwordText = findViewById<EditText>(R.id.editPassword).text;

        GlobalScope.launch(Dispatchers.IO) {
            auth(loginText.toString(), passwordText.toString());
        }
    }

    suspend fun auth(login: String, password: String) {
        val client = HttpClient()
        /*val request: HttpRequest =
            client.request("https://96024908fef05d.lhrtunnel.link/api/auth") {
                method = HttpMethod.Post
                headers {
                    append(HttpHeaders.Accept, "application/json")
                }
                formData {
                    parameter("login", login)
                    parameter("password", password)
                }
            }*/
        val request: HttpResponse =
            client.post<HttpResponse>("https://96024908fef05d.lhrtunnel.link/api/auth") {
                contentType(ContentType.Application.Json)
                headers(Header.)
                formData {
                    parameter("login", login)
                    parameter("password", password)
                }
            }
        println("------------------------------------------------------------------------------------------")
        println(request);
    }*/
    fun Login_onClick() {
        val loginText = findViewById<EditText>(R.id.editTextLogin).text;
        val passwordText = findViewById<EditText>(R.id.editPassword).text;

        sign_in(loginText.toString(), passwordText.toString())
    }


    private fun sign_in(login: String, password: String) {
        val client = OkHttpClient()
        val formBody = FormBody.Builder()
            .add("login", login)
            .add("password", password)
            .build()
        val request = Request.Builder()
            .method("POST", formBody)
            .url("https://96024908fef05d.lhrtunnel.link/api/auth")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    val responseBody = response.body()
                    val jsonObject = JSONObject(responseBody!!.string())
                    val token = jsonObject.getString("token").toString()
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    val log = Logger.getLogger(MainActivity::class.java.name)
                    log.warning("--------------------------" + token)
                }
            }
        })
    }

}