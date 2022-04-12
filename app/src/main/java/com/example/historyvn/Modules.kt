package com.example.historyvn

import com.example.historyvn.viewmodels.CategoriesViewModel
import com.example.historyvn.viewmodels.ObjectsViewModel
import com.example.historyvn.viewmodels.SignInViewModel
import com.example.historyvn.viewmodels.SignUpViewModel
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::SignInViewModel)
    viewModelOf(::SignUpViewModel)
    viewModelOf(::CategoriesViewModel)
    viewModelOf(::ObjectsViewModel)
    single {
        HttpClient(OkHttp) {
            install(Logging)
            install(ContentNegotiation) {
                json(
                    Json {
                        isLenient = true
                        prettyPrint = true
                    }
                )
            }
            defaultRequest {
                url("https://history-vn.herokuapp.com/api/v1/")
            }
        }
    }
}