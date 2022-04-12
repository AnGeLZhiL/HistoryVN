package com.example.historyvn

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.historyvn.databinding.ActivitySiginUpBinding
import com.example.historyvn.models.UserRegisterModel
import com.example.historyvn.viewmodels.SignUpViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class SignUpActivity : AppCompatActivity() {

    private var _binding: ActivitySiginUpBinding? = null
    private val binding: ActivitySiginUpBinding
        get() = _binding ?: throw RuntimeException()

    private val viewModel by viewModel<SignUpViewModel>()


    private val log = "[a-zA-Z0-9]+@[0-9a-z]+\\.+[a-z]{1,3}"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySiginUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textViewSignUp.setOnClickListener {
            LoginOnClick()
        }

        lifecycleScope.launchWhenCreated {
            viewModel.registerState.collectLatest {
                if (it) {
                    finishActivity(0)
                    startActivity(Intent(this@SignUpActivity, News::class.java))
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun SignInOnClick (view: View){
        val signIn = Intent(this, SignIn::class.java)
        finishActivity(0)
        startActivity(signIn)
    }

    fun LoginOnClick() {
        val lastNameText = binding.editLastNameText
        val firstNameText = binding.editFirstNameText
        val middleNameText = binding.editMiddleNameText
        //val birthdayDate = findViewById<EditText>(R.id.editBirthdayDate)
        val loginText = binding.editLoginText
        val passwordText = binding.editPasswordText
        val passwordConfirmationText = binding.editPasswordConfirmationText

        if(lastNameText.text.isNotEmpty() and
            firstNameText.text.isNotEmpty() and
            middleNameText.text.isNotEmpty() and
                //!(birthdayDate.text.length == 0) and
            loginText.text.isNotEmpty() and
            passwordText.text.isNotEmpty() and
            passwordConfirmationText.text.isNotEmpty()
        ){
            if(loginText.text.toString().trim().matches(log.toRegex())){
                if (passwordConfirmationText.text.toString() == passwordText.text.toString()){
                    //sign_up(lastNameText.text.toString(), firstNameText.text.toString(), middleNameText.text.toString(), birthdayDate,
                     //   loginText.text.toString(), passwordText.text.toString(), passwordConfirmationText.text.toString())
                         lifecycleScope.launch {
                             viewModel.register(
                                 UserRegisterModel(
                                     lastName = lastNameText.text.toString(),
                                     midlleName = middleNameText.text.toString(),
                                     firstName = firstNameText.text.toString(),
                                     login = loginText.text.toString(),
                                     password = passwordText.text.toString(),
                                     picture = 1
                                 )
                             ).onFailure {
                                 Toast.makeText(this@SignUpActivity, it.message, Toast.LENGTH_LONG).show()
                             }
                         }

                }
                else passwordConfirmationText.error = "Пароли не совпадают"
            }
            else loginText.error = "Данные заполнены некоректно, логин должен соответствовать почте"
        }
        else {
            if(lastNameText.text.isEmpty()) lastNameText.error = "Поле не заполнено"
            if(firstNameText.text.isEmpty()) firstNameText.error = "Поле не заполнено"
            if (middleNameText.text.isEmpty()) middleNameText.error = "Поле не заполнено"
            //if (birthdayDate.text.length == 0) birthdayDate.error = "Поле не заполнено"
            if (loginText.text.isEmpty()) loginText.error = "Поле не заполнено"
            if (passwordText.text.isEmpty()) passwordText.error = "Поле не заполнено"
            if (passwordConfirmationText.text.isEmpty()) passwordConfirmationText.error = "Поле не заполнено"
        }
    }

    private fun sign_up(
        last_name: String, first_name: String, middle_name: String,
        birthday: EditText, login: String, password: String, password_confirmation:String) {
        /*val client = OkHttpClient()
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
        })*/
    }
}