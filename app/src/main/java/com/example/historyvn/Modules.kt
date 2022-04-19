package com.example.historyvn

import com.example.historyvn.database.InitDatabase
import com.example.historyvn.repositories.CategoryRepository
import com.example.historyvn.viewmodels.*
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::SignInViewModel)
    viewModelOf(::SignUpViewModel)
    viewModelOf(::CategoriesViewModel)
    viewModelOf(::ObjectsViewModel)
    viewModelOf(::TestViewModel)
    viewModelOf(::QuestionViewModel)
    viewModelOf(::ResultsViewModel)
    single {
        InitDatabase(androidContext())
    }
    singleOf(::CategoryRepository)
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