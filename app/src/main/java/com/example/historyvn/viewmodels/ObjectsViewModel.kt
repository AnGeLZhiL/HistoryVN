package com.example.historyvn.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.models.ObjectModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.withContext

class ObjectsViewModel(
    private val client: HttpClient
): ViewModel() {

    suspend fun loadObjects(id: Int) = withContext(viewModelScope.coroutineContext) {
        runCatching {
            client.get("objects/$id").body<List<ObjectModel>>()
        }.getOrElse { emptyList() }
    }

}