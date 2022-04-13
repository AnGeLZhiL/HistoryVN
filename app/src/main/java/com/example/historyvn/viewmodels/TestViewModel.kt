package com.example.historyvn.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.historyvn.models.TestModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.withContext

class TestViewModel(
    private val client: HttpClient
): ViewModel() {
    suspend fun loadObjects(categoryId: Int): List<TestModel> = withContext(viewModelScope.coroutineContext) {
        runCatching {
            client.get("tests/$categoryId").body<List<TestModel>>()
        }.getOrElse { emptyList() }

    }
}