package com.example.historyvn.repositories

import com.example.historyvn.database.tables.Category
import com.example.historyvn.models.CategoryModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.flow
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class CategoryRepository(
    private val httpClient: HttpClient
) {

    fun fetchCategories() = flow {
        emit(
            newSuspendedTransaction {
                Category.all().map {
                    CategoryModel(
                        id = it.id.value,
                        title = it.title,
                        imageUrl = it.imageUrl
                    )
                }
            }
        )
        emit(
            runCatching {
                httpClient.get("categories").body<List<CategoryModel>>()
            }.getOrElse { emptyList() }.also { list: List<CategoryModel> ->
                newSuspendedTransaction {
                    list.forEach {
                        Category.findById(it.id)?.also { category ->
                            category.imageUrl = it.imageUrl
                            category.title = it.title
                        } ?: Category.new {
                            imageUrl = it.imageUrl
                            title = it.title
                        }
                    }
                }
            }
        )
    }

}