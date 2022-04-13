package com.example.historyvn.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.historyvn.User
import com.example.historyvn.models.UserInfoModel
import com.example.historyvn.models.UserRegisterModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

class SignUpViewModel(
    private val client: HttpClient
) : ViewModel() {

    private val _state = MutableStateFlow(false)
    val registerState = _state.asStateFlow()

    suspend fun register(registerModel: UserRegisterModel) =
        withContext(viewModelScope.coroutineContext) {
            runCatching {
                client.post("users/register") {
                    contentType(ContentType.Application.Json)
                    setBody(registerModel)
                }.body<UserInfoModel>()
            }.onSuccess {
                _state.value = true
                User.info = it
            }
        }

}