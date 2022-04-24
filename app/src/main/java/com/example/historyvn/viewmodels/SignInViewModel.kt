package com.example.historyvn.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.historyvn.User
import com.example.historyvn.models.UserInfoModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

class SignInViewModel(
    private val client: HttpClient
): ViewModel() {
    private val _loginState = MutableStateFlow(false)
    val loginState = _loginState.asStateFlow()



    suspend fun login(login: String, password: String) =
        withContext(viewModelScope.coroutineContext) {
            runCatching {
                client.get("users/login") {
                    parameter("login", login)
                    parameter("password", password)
                }.body<UserInfoModel>()
            }.onSuccess {
                _loginState.value = true
                User.info = it
            }
        }

}