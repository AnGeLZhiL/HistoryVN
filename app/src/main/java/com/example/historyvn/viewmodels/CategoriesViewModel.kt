package com.example.historyvn.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.models.CategoryModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.withContext

class CategoriesViewModel(
    private val client: HttpClient
): ViewModel() {

    suspend fun loadCategories(): List<CategoryModel> = withContext(viewModelScope.coroutineContext) {
        runCatching {
            client.get("categories").body<List<CategoryModel>>()
        }.getOrElse { emptyList() }
    }

}