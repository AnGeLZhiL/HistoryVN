package com.example.historyvn

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.util.logging.Logger


class sigin_Up : AppCompatActivity() {

//    val currentDate = findViewById<EditText>(R.id.editBirthdayDate)
//    val dateAndTime = Calendar.getInstance()

    private val log = "[a-zA-Z0-9]+@[0-9a-z]+\\.+[a-z]{1,3}"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sigin_up)
        val textViewSignUp = findViewById<TextView>(R.id.textViewSignUp)
        textViewSignUp.setOnClickListener {
            Login_onClick()
        }
//        setInitialDateTime();
    }

//    fun setDate(v: View?) {
//        DatePickerDialog(
//            this, d,
//            dateAndTime[Calendar.YEAR],
//            dateAndTime[Calendar.MONTH],
//            dateAndTime[Calendar.DAY_OF_MONTH]
//        )
//        .show()
//    }
//
//    var d =
//        OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
//            dateAndTime[Calendar.YEAR] = year
//            dateAndTime[Calendar.MONTH] = monthOfYear
//            dateAndTime[Calendar.DAY_OF_MONTH] = dayOfMonth
//            setInitialDateTime()
//        }
//
//    private fun setInitialDateTime() {
//        currentDate.setText(
//            DateUtils.formatDateTime(
//                this,
//                dateAndTime.timeInMillis,
//                DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR
//                        or DateUtils.FORMAT_SHOW_TIME
//            )
//        )
//    }

    fun SignIn_onClick (view: View){
        val SignIn = Intent(this, SignIn::class.java)
        startActivity(SignIn)
    }

    fun Login_onClick (){
        val lastNameText = findViewById<EditText>(R.id.editLastNameText)
        val firstNameText = findViewById<EditText>(R.id.editFirstNameText)
        val middleNameText = findViewById<EditText>(R.id.editMiddleNameText)
        val birthdayDate = findViewById<EditText>(R.id.editBirthdayDate)
        val loginText = findViewById<EditText>(R.id.editLoginText)
        val passwordText = findViewById<EditText>(R.id.editPasswordText)
        val passwordConfirmationText = findViewById<EditText>(R.id.editPasswordConfirmationText)

        if(!(lastNameText.text.length == 0) and
            !(firstNameText.text.length == 0) and
            !(middleNameText.text.length == 0) and
                !(birthdayDate.text.length == 0) and
            !(loginText.text.length == 0) and
            !(passwordText.text.length == 0) and
                    !(passwordConfirmationText.text.length == 0)){
            if(loginText.text.toString().trim().matches(log.toRegex())){
                if (passwordConfirmationText.text.toString() == passwordText.text.toString()){
                    //sign_up(lastNameText.text.toString(), firstNameText.text.toString(), middleNameText.text.toString(), birthdayDate,
                     //   loginText.text.toString(), passwordText.text.toString(), passwordConfirmationText.text.toString())
                }
                else passwordConfirmationText.error = "Пароли не совпадают"
            }
            else loginText.error = "Данные заполнены некоректно, логин должен соответствовать почте"
        }
        else{
            if(lastNameText.text.length == 0) lastNameText.error = "Поле не заполнено"
            if(firstNameText.text.length == 0) firstNameText.error = "Поле не заполнено"
            if (middleNameText.text.length == 0) middleNameText.error = "Поле не заполнено"
            if (birthdayDate.text.length == 0) birthdayDate.error = "Поле не заполнено"
            if (loginText.text.length == 0) loginText.error = "Поле не заполнено"
            if (passwordText.text.length == 0) passwordText.error = "Поле не заполнено"
            if (passwordConfirmationText.text.length == 0) passwordConfirmationText.error = "Поле не заполнено"
        }
    }

    private fun sign_up(
        last_name: String, first_name: String, middle_name: String,
        birthday: EditText, login: String, password: String, password_confirmation:String) {
        val client = OkHttpClient()
        val formBody = FormBody.Builder()
            .add("last_name", last_name)
            .add("first_name", first_name)
            .add("middle_name", middle_name)
            .add("birthday", birthday.toString())
            .add("login", login)
            .add("password",password)
            .add("password_confirmation",password_confirmation)
            .build()
        val request = Request.Builder()
            .method("POST", formBody)
            .url("https://7f577c2e4794ec.lhrtunnel.link/api/registration")
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