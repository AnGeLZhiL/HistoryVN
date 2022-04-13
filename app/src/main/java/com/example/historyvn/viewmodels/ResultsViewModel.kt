package com.example.historyvn.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.historyvn.User
import com.example.historyvn.models.TestModelWithResult
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.withContext

class ResultsViewModel(
    private val client: HttpClient
) : ViewModel() {


    suspend fun loadResults() = withContext(viewModelScope.coroutineContext) {
        runCatching {
            client.get("tests/results/${User.info.id}").body<List<TestModelWithResult>>()
        }.getOrElse { emptyList() }

    }
}